package com.company.project.lesson1516.customlist;

class MyLinkedListNode<T> {
    // значение, которые добавляется в связный список
    private T value;
    // ссылка на следующую ноду (узел)
    private MyLinkedListNode<T> nextNode;
    // ссылка на предыдущую ноду (узел), если список двунаправленный
    private MyLinkedListNode<T> previousNode;

    public MyLinkedListNode(MyLinkedListNode<T> previousNode,T value, MyLinkedListNode<T> nextNode) {
        this.value = value;
        this.nextNode = nextNode;
        this.previousNode = previousNode;
    }

    public MyLinkedListNode(T value) {
        this.value = value;
    }

    public MyLinkedListNode<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(MyLinkedListNode<T> nextNode) {
        this.nextNode = nextNode;
    }

    public MyLinkedListNode<T> getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(MyLinkedListNode<T> previousNode) {
        this.previousNode = previousNode;
    }

    public T getValue() {
        return value;
    }
}
