# heapq 모듈
written by sohyeon, hyemin, youngcheol 💡

<br>

## heapq 모듈
`heapq모듈`은 이진 트리(binary tree) 기반의 최소 힙(min heap) 자료구조를 제공한다.  
자바의 `PriorityQueue클래스`를 생각하면 된다.  

* 힙은 모든 부모 노드가 자식보다 작거나 같은 값을 갖는 이진 트리  
    * 모든 k에 대해 `heap[k] <= heap[2*k+1] and heap[k] <= heap[2*k+2]`
* 가장 작은 요소가 항상 루트에 위치 => heap[0]

### 최소 힙 생성
`heapq모듈`에는 파이썬의 리스트를 마치 최소 힙처럼 다룰 수 있도록 도와준다. 
* 빈 리스트를 생성하여 heapq 모듈의 함수를 호출할 때마다 이 리스트를 인자로 넘긴다. 
* 최소 힙은 `heapq모듈`을 통해서 원소를 추가하거나 삭제한 리스트를 말한다.

### heappush(heap, item)
`heappush()`는 힙의 불변성을 유지하면서, 힙에 원소를 추가한다.

#### heappush(heap, item) 예제
```
heapq.heappush(heap, 5)
heapq.heappush(heap, 1)
heapq.heappush(heap, 8)
heapq.heappush(heap, 3)
print(heap)
```

결과 : `[1, 3, 8, 5]`  

인덱스 0에 가장 작은 수인 1이 위치하며, 이진 트리에 원소를 추가하는 `heappush() 함수`  는 `O(logN)`의 시간 복잡도를 가진다.  

### heappop(heap)
`heappop()`는 힙의 불변성을 유지하면서, 힙의 원소를 삭제한다.  
* 원소를 삭제할 리스트를 인자로 넘기면, 가장 작은 원소를 삭제 후에 그 값을 리턴한다.  
* 힙이 비어 있으면, `IndexError`가 발생한다.
* pop()하지 않고, 가장 작은 원소에 접근하고 싶을 경우 `heap[0]`을 사용한다.  

#### heappop(heap) 예제
```
print(heapq.heappop(heap))
print(heap) # 제일 작은 원소를 삭제 후에 다시 힙으로 변환 
```

결과 :  
`1`  
`[3, 5, 8]`  

### heapify(x)
`heapify(x)`는 리스트 x를 힙으로 변환한다.  
* `heapify()`에 리스트를 인자로 넘기면 리스트 내부의 원소들이 힙 구조에 맞게 재배치되며, 최솟값이 0번째 인덱스에 위치한다.  
* `heapify()`는 O(N)의 시간 복잡도를 가진다.  

#### heapify(x) 예제
```
List = [3, 5, 2, 8, 1, 4]
heapq.heapify(heap)
print(List)
```

결과 : `[1, 3, 2, 8, 5, 4]`

### 최대 힙 생성
`heapq모듈`은 최소 힙의 기능을 동작하기 때문에 최대 힙을 활용하려면 `튜플(tuple)`을 이용해야 한다.  
* 힙에 튜플(tuple)을 원소로 추가하거나 삭제하면 `튜플 내에서 앞에 있는 값(-i)`을 기준으로 최소 힙이 구성되는 원리를 이용한다.  
* 힙에서 값을 읽어올 때는 각 튜플에서 인덱스 1의 값을 읽어오면 된다.  

#### 최대 힙 예제
```
import heapq

List = [3, 5, 2, 8, 1, 4]
heap = []

for i in List:
    heapq.heappush(heap, (-i, i)) # (우선 순위, 값)

while heap:
    print(heapq.heappop(heap)[1])
```

결과 :  
`8`  
`5`  
`4`  
`3`  
`2`  
`1`  

<br>

## Reference & Additional Resources
* [heapq 모듈 사용법](https://www.daleseo.com/python-heapq/)


