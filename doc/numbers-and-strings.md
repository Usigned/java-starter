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