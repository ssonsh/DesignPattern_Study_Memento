# DesignPattern_Study_Memento

# Notion Link
https://five-cosmos-fb9.notion.site/Memento-4ae6b3b593ca413ab8b9e59c36f9ce22

# 메멘토 (Memento)

### 의도

캡슐화를 위배하지 않은 채 어떤 개게의 내부 상태를 잡아내고 실체화시켜 둠으로써, 이후 해당 객체가 그 상태로 되돌아올 수 있도록 한다.

<aside>
🎈 다른 이름 : 토큰 (Token)

</aside>

**MEMENTO 사전적 의미 : 사람이나 이벤트를 기억하기 위한 물건**

![image](https://user-images.githubusercontent.com/18654358/157772249-8347d31a-3a9e-477b-a900-9961ae111b64.png)

- 기억하는 물건 EX : 과거의 사진

<aside>
🎈 Object의 상태를 사진처럼 박아서 Memento로 만들어 보관하고 나중에 필요할 때 꺼내서 그 때의 상태로 되돌릴 수 있는 패턴

- 일반적으로 어떤 Object의 history를 관리할 때 유용한 패턴이 된다.

</aside>

![image](https://user-images.githubusercontent.com/18654358/157772262-57e7ed16-cf0d-4240-9fb4-63f9952d740c.png)

**Object 클래스**

- 해당 Object를 나타내는 **내부 State** 가 있다.
- 이 Object의 상태에 대한 Memento를 만들어주는 **“createMemento()”** 함수
- 그리고 Memento Object 로 부터 내부 State를 보관해주는 **“restore()”** 함수가 메소드로 제공되어야 한다.

**두 함수에서 사용될 Memento 클래스**

- Object의 **state**를 그대로 저장해서 관리해야 하기 때문에
- **똑같은 state**를 프로퍼티로 가지고 있다.

구조가 달라보일 수 있다. 그러나 → **핵심 구조는 모두 같다.**

- 관리하고자 하는 **Object에서 그 State를 Memento로 뽑아주는 함수**
- **Memento로 부터 그 State를 보관해주는** restore 함수가 제공
- Object의 State를 저장하기 위한 **Memento 클래스**가 정의되면 메멘토 패턴이라 볼 수 있다.

### 예시

![image](https://user-images.githubusercontent.com/18654358/157772318-b0544910-60c9-497f-b003-ddddae8f7ee8.png)


**Cat.java**

```java
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
```

CatMemento.java

```java
import java.time.LocalDateTime;
import java.util.UUID;

public class CatMemento {
    private int age;
    private int height;

    private UUID uuid;
    private LocalDateTime createdTime;

    public CatMemento(int age, int height) {
        this.age = age;
        this.height = height;
        this.uuid = UUID.randomUUID();
        this.createdTime = LocalDateTime.now();
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "CatMemento{" +
               "age=" + age +
               ", height=" + height +
               ", uuid=" + uuid +
               ", createdTime=" + createdTime +
               '}';
    }
}
```

테스트

```java
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
```

```java
## 지금 고양이는?
		 3 age / 16 height
## 지금까지의 고양이 히스토리 출력 - 시작 
		CatMemento{age=0, height=10, uuid=9c967f03-1737-4c07-be09-bedc43eb92c5, createdTime=2022-02-16T09:18:59.943783600}
		CatMemento{age=1, height=12, uuid=b01ab3eb-0218-49f8-8ac3-ffbb7d1cfba7, createdTime=2022-02-16T09:18:59.944823400}
		CatMemento{age=2, height=14, uuid=71462afb-b279-436b-ad88-aee1133e7a5f, createdTime=2022-02-16T09:18:59.944823400}
		CatMemento{age=3, height=16, uuid=0e69b087-5d7e-4997-a976-f5087bedacb8, createdTime=2022-02-16T09:18:59.944823400}
## 지금까지의 고양이 히스토리 출력 - 종료 
## 1살 때로 돌아가고 싶다 ~ (history의 1번째 index)
		 1 age / 12 height
```

### 활용성

- 어떤 객체에 대한 스냅샷(몇 개의 일부)를 저장한 후 나중에 이 상태로 복구해야 할 때
- 상태를 얻는 데 필요한 직접적인 인터페이스를 두면 그 객체의 구현 세부 사항이 드러날 수 밖에 없고, 이것으로 객체의 캡슐화가 깨질 때

### 구조 (in WiKi)

![image](https://user-images.githubusercontent.com/18654358/157772350-9958f1ba-5bf7-4040-934e-4f49c3a1f0fb.png)

### 참여자

**Memento**

- 원조본 객체의 내부 상태를 저장한다.
- 메멘토는 원조본 객체의 내부 상태를 필요한 만큼 저장해둔다.
- 메멘토는 원조본 객체를 제외한 다른 객체는 자신에게 접근할 수 없도록 막는다.
- 그래서 Memento 클래스에는 사실상 두 종류의 인터페이스가 있다.
- 관리 책임을 갖는 Caretaker 클래스는 Memento에 대한 제한 범위(narrow) 인터페이스만 볼 수 있다.
    - 즉, Caretaker는 메멘토를 다른 객체에 넘겨줄 수만 있다.
    - 즉, 자신의 상태를 이전 상태로 복원하기 위해 필요한 모든 자료에 접근하게 해주는 인터페이스이다.
- 이상적으로는 메멘토를 생성하는 원조본 객체만이 메멘토의 내부 상태에 접근할 수 있는 권한을 가진다.

**Originator**

- 원조본 객체이다.
- 메멘토를 생성하여 현재 객체의 상태를 저장하고 메멘토를 사용하여 내부 상태를 복원한다.

**Caretaker**

- 메멘토의 보관을 책임지는 보관자이다.
- 메멘토의 내용을 검사하거나 그 내용을 건드리지는 않는다.
