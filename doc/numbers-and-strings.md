# Numbers and Strings

## Numbers
Why we need a **Number** object
1. argument of a method that expects an object (often used when manipulating collections of numbers)
2. To use constants defined by the class.
3. To use class methods for converting values and from other types.

**Formatting Numeric Print Output**

class _Print__Stream_

usage: _System.out.format(...)_;

## Strings
**creating**
```
char[] chars = { 'h', 'e', 'l', 'l', 'o', '.' };
String s = new String(chars);
```
Methods
```
((String)s).length();
((String)s).charAt(index);
((String)s).substring(startIndex, endIndex);
```
**StringBuilder**

StringBuilder obj are like String obj, except they can be modified.

Strings should always be used unless string builders offer an advantage in terms of simpler code (see the sample program at [Demo](../src/strings/Demo.java)) or better performance.


## Autoboxing and Unboxing
The Java compiler applies autoboxing when a primitive value is:
- Passed as a parameter to a method that expects an object of the corresponding wrapper class.
- Assigned to a variable of the corresponding wrapper class.

The Java compiler applies unboxing when an object of a wrapper class is:
- Passed as a parameter to a method that expects a value of the corresponding primitive type.
- Assigned to a variable of the corresponding primitive type.

## Exercise
An anagram is a word or a phrase made by transposing the letters of another word or phrase; for example, "parliament" is an anagram of "partial men," and "software" is an anagram of "swear oft." Write a program that figures out whether one string is an anagram of another string. The program should ignore white space and punctuation.

Hints: first remove non-letter chars, then sort, finally compare.

see [StringExe.java](../src/exercise/StringExe.java)