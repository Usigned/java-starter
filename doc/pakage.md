# Packages
A package is a grouping of related types providing access protection and name space management. 
Note that types refers to classes, interfaces, enumerations, and annotation types.
Enumerations and annotation types are special kinds of classes and interfaces, respectively, so types are often referred to simply as classes and interfaces.

## Summary

To create a package for a type, put a package statement as the first statement in the source file that contains the type (class, interface, enumeration, or annotation type).

To use a public type that's in a different package, you have three choices: (1) use the fully qualified name of the type, (2) import the type, or (3) import the entire package of which the type is a member.

The path names for a package's source and class files mirror the name of the package.

You might have to set your CLASSPATH so that the compiler and the JVM can find the .class files for your types.