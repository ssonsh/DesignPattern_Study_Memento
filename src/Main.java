import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<CatMemento> catHistory = new ArrayList<>();

        Cat cat = new Cat(0, 10);
        CatMemento memento_0 = cat.createMemento();
        catHistory.add(memento_0);

        cat.growup(1, 12);
        catHistory.add(cat.createMemento());

        cat.growup(2, 14);
        catHistory.add(cat.createMemento());

        cat.growup(3, 16);
        catHistory.add(cat.createMemento());

        System.out.println("## 지금 고양이는?");
        cat.speak();

        System.out.println("## 지금까지의 고양이 히스토리 출력 - 시작 ");
        for (CatMemento catMemento : catHistory) {
            System.out.println("\t\t" + catMemento.toString());
        }
        System.out.println("## 지금까지의 고양이 히스토리 출력 - 종료 ");

        System.out.println("## 1살 때로 돌아가고 싶다 ~ (history의 1번째 index)");
        cat.restore(catHistory.get(1));
        cat.speak();

    }
}
