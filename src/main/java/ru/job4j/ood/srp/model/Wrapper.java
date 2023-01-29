package ru.job4j.ood.srp.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "wrapper")
public class Wrapper<T extends Person> {
    private List<T> obj;

    public Wrapper() {
    }

    public Wrapper(List<T> obj) {
        this.obj = obj;
    }

    public List<T> getObj() {
        return obj;
    }

    public void setObj(List<T> obj) {
        this.obj = obj;
    }
}
