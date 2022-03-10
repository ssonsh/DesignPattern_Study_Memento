public class Cat {
    private int age;
    private int height;

    public Cat(int age, int height) {
        this.age = age;
        this.height = height;
    }

    public void speak(){
        System.out.println(String.format("\t\t %s age / %s height", this.age, this.height));
    }

    public CatMemento createMemento(){
        return new CatMemento(this.age, this.height);
    }

    public void restore(CatMemento catMemento){
        this.age = catMemento.getAge();
        this.height = catMemento.getHeight();
    }

    public void growup(int age, int height) {
        this.age = age;
        this.height = height;
    }
}
