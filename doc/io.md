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

