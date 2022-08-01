package testresp;

/**
 * @description: s
 * @author: frank.wu
 * @create: 2021-11-05
 */
public class User{
    public Integer age;
    public String s;
    public Integer sex;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public User(Integer age, String s, Integer sex) {
        this.age = age;
        this.s = s;
        this.sex = sex;
    }
}


