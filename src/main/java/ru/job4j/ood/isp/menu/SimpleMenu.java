package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {
    private final List<MenuItem> rootElements = new ArrayList<>();

    private Optional<ItemInfo> findItem(String name) {
        ItemInfo itemInfo = null;
        DFSIterator dfsIterator = new DFSIterator();
        while (dfsIterator.hasNext()) {
            ItemInfo itNext = dfsIterator.next();
            if (itNext.menuItem.getName().equals(name)) {
                itemInfo = itNext;
                break;
            }
        }
        return Optional.ofNullable(itemInfo);
    }

    private static class SimpleMenuItem implements MenuItem {
        private final String name;
        private final List<MenuItem> children = new ArrayList<>();
        private final ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {
        private final Deque<MenuItem> stack = new LinkedList<>();
        private final Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private record ItemInfo(MenuItem menuItem, String number) {
    }

    public Iterator<MenuItemInfo> iterator() {
        DFSIterator dfsIterator = new DFSIterator();
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                var it = dfsIterator.next();
                return new MenuItemInfo(it.menuItem, it.number);
            }
        };
    }

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        Optional<ItemInfo> parent = findItem(parentName);
        parent.map(itemInfo -> itemInfo.menuItem.getChildren()
                        .add(new SimpleMenuItem(childName, actionDelegate)))
                .orElseGet(() -> rootElements.add(new SimpleMenuItem(childName, actionDelegate)));
        return true;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        MenuItemInfo rsl = null;
        Optional<ItemInfo> find = findItem(itemName);
        if (find.isPresent()) {
            for (MenuItemInfo itNext : this) {
                if (itNext.getName().equals(find.get().menuItem.getName())) {
                    rsl = itNext;
                    break;
                }
            }
        }
        return Optional.ofNullable(rsl);
    }
}
