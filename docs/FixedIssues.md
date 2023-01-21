A list of issues that have not been resolved in `jackson-module-kotlin`, but have been or will be resolved in `kogera`.

## Fixed
- [Update `KotlinModule` to override `AnnotationIntrospector.findCreatorAnnotation()` instead of `hasCreatorAnnotation()` · Issue \#200](https://github.com/FasterXML/jackson-module-kotlin/issues/200)
- [Depending on kotlinx\.reflect\.lite instead of kotlin\.reflect · Issue \#213](https://github.com/FasterXML/jackson-module-kotlin/issues/213)
- [Jackson skips isXXX properties with Int type · Issue \#337](https://github.com/FasterXML/jackson-module-kotlin/issues/337)
- [Regression on data binding in 2\.11\.0 with specific field naming · Issue \#340](https://github.com/FasterXML/jackson-module-kotlin/issues/340)
- [Private getter with different return type hides property · Issue \#341](https://github.com/FasterXML/jackson-module-kotlin/issues/341)
- [Field names containing hyphens are truncated when serialized · Issue \#434](https://github.com/FasterXML/jackson-module-kotlin/issues/434)
- [Remove \`kotlin\-reflect\` and replace it with \`kotlinx\-metadata\-jvm\` · Issue \#450](https://github.com/FasterXML/jackson-module-kotlin/issues/450)
- [Field names are being lowercased automatically when the first word of the camel\-case is too short · Issue \#475](https://github.com/FasterXML/jackson-module-kotlin/issues/475)
- [Failed deserialization of fields where only the first character is lowercase · Issue \#503](https://github.com/FasterXML/jackson-module-kotlin/issues/503)
- [Remove deprecated method in KNAI · Issue \#508](https://github.com/FasterXML/jackson-module-kotlin/issues/508)
- [ObjectMapper serialization for boolean fields named isFieldName changed between versions 2\.10\.5 and 2\.12\.4 · Issue \#552](https://github.com/FasterXML/jackson-module-kotlin/issues/552)
- [Are there any special requirements to working with kotlin\.time\.Duration? · Issue \#544](https://github.com/FasterXML/jackson-module-kotlin/issues/544)
- [Serialization of is\-getter with continuous capital letters · Issue \#545](https://github.com/FasterXML/jackson-module-kotlin/issues/545)
- [Minor bugs in \`SimpleModule\.addSerializer\`/\`addDeserializer\` · Issue \#558](https://github.com/FasterXML/jackson-module-kotlin/issues/558)
- [Regression: is\-getter detection error · Issue \#559](https://github.com/FasterXML/jackson-module-kotlin/issues/559)
- [Field from a Kotlin data class gets ignored if it's prefixed with "is" but not a boolean\. · Issue \#561](https://github.com/FasterXML/jackson-module-kotlin/issues/561)
- [How to properly configure an ObjectMapper to handle kotlin\-specific integer types? · Issue \#573](https://github.com/FasterXML/jackson-module-kotlin/issues/573)
- [objectMapper\.writeValueAsString does not properly serialize Kotlin data class variables that begin with "is" · Issue \#575](https://github.com/FasterXML/jackson-module-kotlin/issues/575)
- [Lazy load UNIT\_TYPE · Issue \#580](https://github.com/FasterXML/jackson-module-kotlin/issues/580)
- [Can I deserialize a data class with an unsigned member in the constructor? · Issue \#591](https://github.com/FasterXML/jackson-module-kotlin/issues/591)
- [Serialization of flatRate and isFlatRate are mixed up\. · Issue \#600](https://github.com/FasterXML/jackson-module-kotlin/issues/600)
- [Parsing of UShort uses incorrect ranges · Issue \#611](https://github.com/FasterXML/jackson-module-kotlin/issues/611)
- [`JsonSerializer` is enabled when the value is an Object type with non\-null value and the property definition is nullable. · Issue \#618](https://github.com/FasterXML/jackson-module-kotlin/issues/618)

## Maybe fixed(verification required)
- [@JsonProperty is ignored on data class properties with names starting with "is" · Issue \#237](https://github.com/FasterXML/jackson-module-kotlin/issues/237)

## Want to fix
- [Support for inline classes · Issue \#199](https://github.com/FasterXML/jackson-module-kotlin/issues/199)
- [Getting MismatchedInputException instead of MissingKotlinParameterException · Issue \#234](https://github.com/FasterXML/jackson-module-kotlin/issues/234)
- [\`@JsonProperty\` annotation is not working for fields with type of value class\. · Issue \#540](https://github.com/FasterXML/jackson-module-kotlin/issues/540)
- [There are some problems with KNAI\.hasCreatorAnnotation · Issue \#547](https://github.com/FasterXML/jackson-module-kotlin/issues/547)
- [MissingKotlinParameterException should have a reference to KCallable · Issue \#548](https://github.com/FasterXML/jackson-module-kotlin/issues/548)
- [This module shouldn't bring kotlin\-reflect 1\.5 as a transitive dependency · Issue \#566](https://github.com/FasterXML/jackson-module-kotlin/issues/566)
- [MissingKotlinParameterException contains non\-serializable KParameterImpl, breaking Throwable contract · Issue \#572](https://github.com/FasterXML/jackson-module-kotlin/issues/572)
- [ReflectionCache takes a lot of memory · Issue \#584](https://github.com/FasterXML/jackson-module-kotlin/issues/584)