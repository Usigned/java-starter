# Basic I/O

## I/O Streams

An I/O stream represents an input source or an output destination. 

A stream can represent many different kinds of sources and destinations, including:

- disk files
- devices
- other programs
- memory arrays

Streams support different kinds of data, including:
- simple bytes
- primitive data types
- localized characters
- objects

All streams present the same simple model to programs: 

A stream is a sequence of data. A program uses an input stream to read data from a source, one item at a time:

![Reading information into a program.](https://docs.oracle.com/javase/tutorial/figures/essential/io-ins.gif)

A program uses an *output stream* to write data to a destination, one item at time:

![Writing information from a program.](https://docs.oracle.com/javase/tutorial/figures/essential/io-outs.gif)

### Byte Streams

All byte stream classes are descended from `InputStream` and `OutputStream`.

File I/O byte streams:  `FileInputStream` and `FileOutputStream`.

Other kinds of byte streams differ mainly in the way they are constructed.

Note:

- Always close streams, use `finally` or `try-with-resource`

- Byte streams should only be used for the most primitive I/O.

  - other streams types are all built on byte streams.

    

### Character Streams

Character values are stored using Unicode conventions in Java platform. 

Character stream I/O automatically translates this format to and from the local character set, such as ASCII. 

Using Character stream inside of Byte stream for program internationalization.

Descended from `Reader`  and `Writer`.

File I/O: `FileReader`, `FileWriter`

Character streams are often "wrappers" for byte stream.

- uses [Byte Streams](#Byte streams) to perform physical I/O
- character stream handles translation

Two general-purpose byte-to-character "bridge" streams to use when no prepackages character stream classes that meet your needs.

- `InputStreamReader`
-  `OutputStreamWriter`

**Line-Oriented I/O**

Allows programs to read text files created on any of the widely used operating systems.

`BufferedReader` and `PrintWriter`

### Buffered Streams

Using unbuffered I/O means each read or write request is handled directly by the underlying OS. This can make a program much **less efficient**, since each such request often triggers disk access, network activity, or some other operation that is relatively **expensive**.

Convert unbuffered stream to buffered stream, four buffered stream classes to wrap unbuffered streams:

- buffered byte streams
  - `BufferedInputStream` and `BufferedOutputStream`
- buffered character streams
  - `BufferedReader` and `BufferedWirter`

**flushing buffer**

writer out a buffer at critical points without waiting for it to fill.

- autoflush: certain key events cause the buffer to be flushed, such as `println` or`format`

- flush manually: `flush` method is valid on any output stream, but has no effect unless the stream is buffered

### Scanning and Formatting

**Scanning**

`Scanner` can be used to **break down** formatted input into tokens and translate individual tokens according to their data type.

`Scanner`, by default, uses white space to separate tokens. To use a different token separator, invoke `useDelimiter()`, specifying a regular expression.

```java
s.useDelimiter(",\\s*").
```

**Translating Individual Tokens**

`Scanner` supports tokens for all of the Java language's primitive types.

**Formatting**

Stream objects are instances of either 

- `PrintWriter`: a character stream
  - usually used
- `PrintStream`:  a byte stream
  - only `System.out` or `System.err` you likely to need

both implement the same set of methods for converting internal data into formatted output.

Two levels of formatting are provided:

- `print` and `println`
- `format`
  - `System.out.format("The square root of %d is %f.%n", i, r);`
  - use `%n` instead of `\n`

<<<<<<< HEAD
### Data Streams

- binary I/O of  primitive data type values and String values.

- implement either `DataInput` or `DataOutput` Interface.

- most widely-used implementations `DataInputStream`, `DataOutputStream`
- **don't support `Object` type**

### Object Streams

- I/O of objects, classes need to implement the `Serializable` interface
- Interface: `ObjectInput`, `ObjectOutput` sub-interfaces of `DataInput` and `DataOutput`
- Implementations: `ObjectInputStream` and `ObjectOutputStream`

- If `readObject()` doesn't return the object type expected, attempting to cast it to the correct type may throw a [`ClassNotFoundException`](https://docs.oracle.com/javase/8/docs/api/java/lang/ClassNotFoundException.html).

**Output and Input of Complex Objects**

- If `readObject` is to reconstitute an object from a stream, it has to be able to reconstitute all of the objects the original object referred to.

![I/O of multiple referred-to objects](https://docs.oracle.com/javase/tutorial/figures/essential/io-trav.gif)

- a stream can only contain on copy of an object and any number of references to it.

```java
Object ob = new Object();
out.writeObject(ob);
out.writeObject(ob);

Object ob1 = in.readObject();
Object ob2 = in.readObject();
```

​	`ob1` and `ob2` will refer to a single object.

- if a single object is written to two streams, it will be duplicated.

## File I/O

### What Is a Path?

- relative or absolute

- symbolic link (or soft link)

  - transparent to applications, automatically redirect to the target.

  ![Sample symbolic link](https://docs.oracle.com/javase/tutorial/figures/essential/io-symlink.gif)

  - resolving a link: substitute the actual location for soft link.

=======
### I/O from the Command Line

two ways of interaction:

- Standard Streams - byte streams

  read input from the keyboard and write output to the display

  - Standard Input: `System.in`
    - To use as a character stream: wrap in `InputStreamReader`
  - Standard output: `System.out`
  - Standard Error: `System.err`

- console - character streams: `reader` and `writer` methods

  - Useful for secure password entry: `readPassword` method

  - To retrieve the Console object: `System.console()`

  use `Console` to write a program to change user's password, see [Password.java](../src/io/Password.java)

## File  I/O 

### The Path class

- creating a path 

  `Path path = Paths.get(URL);` 

- retrieving information about a path

  ```java
  Path path = Paths.get(URL)
  System.out.format("toString: %s%n", path.toString());
  System.out.format("getFileName: %s%n", path.getFileName());
  System.out.format("getName(0): %s%n", path.getName(0));
  System.out.format("getNameCount: %d%n", path.getNameCount());
  System.out.format("subpath(0,2): %s%n", path.subpath(0,2));
  System.out.format("getParent: %s%n", path.getParent());
  System.out.format("getRoot: %s%n", path.getRoot());
  ```

  output is Operating System dependent.

- convert path 
  - To a string that can be opened from a browser: `toUri`
  - To absolute path: `toAbsolutePath`

- joining two paths
  - `resolve()`

### File Operations

- `Files` class offers a set of static method for file and directory operations.
-  `Files` methods work on instance of `Path` objects.

- all methods that access the file system can throw an `IOException`
>>>>>>> bd8ebd4b5173a6667ce31e2df37ef7bc1ca6f7db
