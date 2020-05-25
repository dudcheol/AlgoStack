# 💡 PS Tips In Java

## 1. 자주 실수하는 부분

### 1-1. `얕은 복사`와 `깊은 복사`
``` java
int[] a = new int[];
int[] b = a.clone();
```
1차원 배열은 clone을 통해 깊은 복사가 가능하다. 하지만 2차원 배열은 clone으로 복사가 불가능!

왜냐하면, 2차원 배열의 요소들이 주소참조를 하는 형태이기 때문이다. 다음과 같은 처리로 간단하게 복사할 수 있다.
``` java
char[][] deepCopy(char[][] origin) {
        char[][] result = new char[origin.length][origin[0].length];
        for (int i = 0; i < origin.length; i++) {
            result[i] = origin[i].clone();
        }
        return result;
}
```
    



## 2. 알고리즘 최적화

### 1-1. 입력

#### `Scanner`와 `BufferedReader`의 차이



## 3. 유용한 스킬

### 1-1. 정렬

#### `HashMap`의 key와 value로 정렬하는 방법
``` java
ArrayList<Map.Entry<>> list = new ArrayList<>(Map객체);
Collections.sort(list, new Comparator() { ... })
```
* Map.Entry를 가지는 List를 생성하고, ArrayList를 초기화 시 파라미터로 Map객체를 넘긴다
* Collections 클래스를 사용하여 생성한 List와 Comparator에서 compare함수를 오버라이딩하여 정렬을 구현한다.
