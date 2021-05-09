# Annotation

a form of metadata, no direct effect on the operation of code they annotate

usages:
- information for compiler: detect errors or suppress warning 
- Compile-time and deployment-time processing: used by tools to generate code, XML files or others
- runtime processing: examined at runtime

Annotation can be applied to 
- declarations
- the use of ype

## Declaring
```java
@interface ClassPreamble {
    String author();
    String date();
    int currentRevision() default 1;
    String lastModified() default "N/A";
    String lastModifiedBy() default "N/A";
    // Note use of array
    String[] reviewers();
}
```

## Predefined Annotation Type

predefined annotations in java SE API

- @Deprecated
- @Override
- @SuppressWarnings
- @FunctionalInterface
