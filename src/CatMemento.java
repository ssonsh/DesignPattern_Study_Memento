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
