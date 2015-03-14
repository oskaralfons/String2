# String2

String2 is a little library for String intensive applications. String2 is like JVMs StringDeduplication but deduplicates singular words in a String. Ehhmm...What?  
String2 provides a naive dictionary and stores every word (limited only by whitespaces) as a single int-value. So at at the end of the day String2 will contains instead an array of Chars an array of int values. Every int-value represents a single word stored in a Dict (String <-> Int). Yes, this makes absolute no sense for a standart application, but makes extremely sense for string sensitive applications, like a lightweigt collection of all wikipedia page titles and links.  

#### How to use 

```
// will create a String2 and add 'I' 'like' 'String' '2' 'more' 'than' 'usual' 'Strings' to the dictionary
// myString will only contain an array with [-1,2,-3,4,5,6,7,-8]
final String2 myString = new String2("I like String 2 more than usual Strings");
// will map 'i' 'like' 'more' 'than' to int values from existing dict
// myOtherString  will only contain an array with [1,2,9,5,6,10]
// we also save memory for same lowercase/uppercase words 
final String2 myOtherString = new String2("i like vanilla more than chocolate");
```

..and yes, I know my Englishh is terrible.