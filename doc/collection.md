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

- used where maximum generality is desired

- can be converted to other collection implementations
- contains methods performs basic operations such as:
  - `int size()`
  - `boolean isEmpty()`
  -  `boolean contains(Object element)`,
  - `boolean add(E element)`,
  - `boolean remove(Object element)`
  - `Iterator<E> iterator()`

- contains methods operate on entire collections -- bulk operations:
  - `boolean containsAll(Collection<?> c)`
  - `boolean addAll(Collection<? extends E> c)` 
  - `boolean removeAll(Collection<?> c)`
  -  `boolean retainAll(Collection<?> c)`
  - `void clear()`

- additional methods for array operations
  - `Object[] toArray()`
  - `<T> T[] toArray(T[] a)`

- in JDK 8, also exposes methods for obtaining sequential or parallel streams from collection
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

### Bulk Operations

- perform an operation on an entire `Collection`

- usually more efficient than basic operations

### Array Operations

- bridge between collections and arrays