package ru.job4j.queue;

import java.util.Deque;

public class ReconstructPhrase {
    private final Deque<Character> descendingElements;
    private final Deque<Character> evenElements;

    public ReconstructPhrase(Deque<Character> descendingElements, Deque<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        StringBuilder sbd = new StringBuilder();
        int deqSize = evenElements.size();
        for (int i = 0; i < deqSize; i++) {
            if (i % 2 == 0) {
                sbd.append(evenElements.peek());
            }
            evenElements.poll();
        }
        return sbd.toString();
    }

    private String getDescendingElements() {
        StringBuilder sbd = new StringBuilder();
        int deqSize = descendingElements.size();
        for (int i = 0; i < deqSize; i++) {
            sbd.append(descendingElements.pollLast());
        }
        return sbd.toString();
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}
