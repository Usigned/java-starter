# Collection

**Collections Framework**

an unified architecture for representing and manipulating collections.

- Interfaces
- Implementations
- Algorithms

# Interface

*core collection interfaces* -- foundation of Java Collections Framework

![Two interface trees, one starting with Collection and including Set, SortedSet, List, and Queue, and the other starting with Map and including SortedMap.](https://docs.oracle.com/javase/tutorial/figures/collections/colls-coreInterfaces.gif)

> Note:
>
> 1. the hierarchy consist of two distinct trees `Map` & `Collection` 
>
> 2. all the core collection interfaces are generic.

Specifics:

1. `Collection`
   - the least common denominator that all collections implement
   - Java doesn't provide any direct implementations of this interface
2. `Set`
   - not duplicate elements
3. `List`
   - an ordered collection

4. `Queue`
   - hold multiple elements prior to processing
   - typically, but do not necessarily, order elements in a FIFO manner

5. `Deque`
   - hold multiple elements prior to processing
   - can be used both as FIFO and LIFO
6. `Map`
   - an object maps keys to values
7. `SortedSet` and `SortedMap`
   - sorted versions of `Set` and `Map`

## The Collection Interface

### Usage

- used where maximum generality is desired
- can be converted to other collection implementations

### Collection Basic Operations

contains methods performs basic operations such as:

- `int size()`
- `boolean isEmpty()`
- `boolean contains(Object element)`,
- `boolean add(E element)`,
- `boolean remove(Object element)`
- `Iterator<E> iterator()`

### Collection Bulk Operations

- perform an operation on an entire `Collection`

- usually more efficient than basic operations

- bulk operations:
  - `boolean containsAll(Collection<?> c)`
  - `boolean addAll(Collection<? extends E> c)` 
  - `boolean removeAll(Collection<?> c)`
  - `boolean retainAll(Collection<?> c)`
    - remove all element that are not also contained in another collection
  - `void clear()`

### Collection Array Operations

- bridge between collections and arrays

- additional methods for array operations
  - `Object[] toArray()`
  - `<T> T[] toArray(T[] a)`

In JDK 8, also exposes methods for obtaining sequential or parallel streams from collection
- `Stream<E> stream()` 
- `Stream<E> parallelStream()`

### Traversing Collections

three ways to traverse collections

- aggregate operations

  - **do not modify the underlying collection**

- `for-each`

- use `Iterator`

  `Iterator` interface

  ```java
  public interface Iterator<E> {
      boolean hasNext();
      E next();
      void remove(); //optional
  }
  ```

  - `remove` method removes the last element returned by `next` from the underlying collection
  - `remove` may be called only once per call to `next` and throws an exception if this rule is violated.

  > Note that `Iterator.remove` is the *only* safe way to modify a collection during iteration; the behavior is unspecified if the underlying collection is modified in any other way while the iteration is in progress.

  use `Iterator` to filter an arbitrary `Collection` -- polymorphic

  ```java
  static void filter(Collection<?> c) {
      for (Iterator<?> it = c.iterator(); it.hasNext(); )
          if (!cond(it.next()))
              it.remove();
  }
  ```
  
  Use `Iterator` instead of `for-each` when you want to:
  
  - remove current element: `for-each` hide the`Iterator` and cannot do filtering
  - iterate over multiple  collections in parallel

## The Set Interface

- a `Collection` cannot contain duplicate elements
- `Set` contains only methods inherited from `Collection` and adds restriction
- `Set` also adds a stronger contract on `equals` and `hashCode`, this allows `Set` instance to be compared even if their implementations are different

Java have three `Set` implementations:

- `HashSet`
  - store elements in a hash table
  - the best-performing implementation
  - no guarantees the order of iteration: chaotic order
- `TreeSet`
  - a ***red-black tree***
  - orders its elements based on their values
  - much slower than `HashSet`
- `LinkedSet` 
  - a hash table with a linked list
  - orders elements based on their insertion order
  - only slightly slower than `HashSet`

If you have a `Collection c` and you want to create another `Collection` with no duplicated elements, you can do: (a useful `Set` idiom):

```java
Collection<Type> noDups = new HashSet<Type>(c);
```

or, in JDK 8:

```java
c.stream().collect(Collectors.toSet()); // no gurantee of the set type
c.stream().collect(Collectors.toCollection(TreeSet::new))
```

### Set Basic Operations

basic the same as [`Collection`interface](# Collection Basic Operations) but with restrictions

- `size` 
- `isEmpty`
- `add` add element to `Set` if it's not already present, return whether it's added
- `remove` removes element if it's present, return whether it's removed

- `Iterator` return `Iterator` over the `Set`

### Set Bulk Operations

perform standard set-algebraic operations

- `s1.containsAll(s2)`: return `true` if s2 is a subset of s1

- `s1.addAll(s2)`: transform s1 into **union** of s1 and s2

- `s1.retainAll(s2)`: transform s1 into **intersection** of s1 and s2

- `s1.removeAll(s2)`: transform s1 into **(asymmetric)set difference** of s1 and s2

>  Note: to calculate *nondestructively*, caller must copy one set before calling

### Set Array Operations

same as [Collection Array Operations](#Collection Array Operations)

## The List Interface

- ordered `Collection` also called *sequence*

- include operations for:

  - `Positional access`: manipulates elements based on position

  - `Search` of specific object and return its position

  - `Iteration`

  - `Range-view`: the `sublist` method 

Java have two `List` implementations:

- `ArrayList`
  - usually the better-performing implementation
- `LinkedList`
  - better perform under certain circumstances

### Collection Operations

same as [Collection Basic Operations](#Collection Basic Operations) and [Collection Bulk Operations](#Collection Bulk Operations)

Something to notice:

- `remove` always remove the first elements that occurs
- `add` and `addAll` always append new elements to the end of the list

- like `Set`,  `List` strengthens the requirements on the `equals` and `hashCode` methods so that two `List` objects can be compared for logical equality without regard to their implementation classes

### Positional Access and Search Operations

basic `positional acess` operations

- `get`
- `set`
- `add`
- `remove`

Other operations:

- `indexOf`
- `lastIndexOf`

> Note: here `add` and `remove` are different from `Collection` operations, it take index as param

`addAll` insert all elements of the specified `Collection` starting at the specified position. The elements are inserted in order they are returned by specified `Collection`'s iterator

> The `Arrays` class has a static factory method called `asList`, which allows an array to be viewed as a `List`. This method does not copy the array. Changes in the `List` write through to the array and vice versa. The resulting List is not a general-purpose `List` implementation, because it doesn't implement the (optional) `add` and `remove` operations: Arrays are not resizable. 

### Iterators

`ListIterator` allows you to traverse the list

- in either direction
- modify list during iteration
- obtain the current position of the iterator

`ListIterator` interface:

- `hasNext`, `next` and `remove` do the same as [`Iterator`](#Traversing Collections)

- `hasPrevious` and `previous` kind like `hasNext` and `next`

- `nextIndex` and `previousIndex` return the index of the returned element
- `remove` removes the last element returned by `next` or `previous`

- additional ways to modify list

  - `set` overwrites the last element return by `next` or `previous`

  - `add` insert a new element before the current cursor position