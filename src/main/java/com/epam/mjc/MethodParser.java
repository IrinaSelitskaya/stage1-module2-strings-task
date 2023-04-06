package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String accessModifier;
        String returnType;
        String methodName=null;
        String[] twoSubstrings = signatureString.split("\\(|\\)");
        String[] firstSubstring = twoSubstrings[0].split("\\s");
        if(firstSubstring.length==3) {
            accessModifier = firstSubstring[firstSubstring.length - 3];
        }else {
          returnType=firstSubstring[firstSubstring.length-2];
          methodName = firstSubstring[firstSubstring.length - 1];}
        if(twoSubstrings.length==1){
            return new MethodSignature(methodName);
        } else {
            List<MethodSignature.Argument> arguments= new ArrayList<>();
            String[]secondSubstring=twoSubstrings[1].split(", ");
            for (int i = 0;i<secondSubstring.length;i++){
                String[]argumentArray=secondSubstring[i].split(" ");
                String type=argumentArray[0];
                String name=argumentArray[1];
            MethodSignature.Argument argument=new MethodSignature.Argument(type,name);
            arguments.add(argument);}
            return new MethodSignature(methodName,arguments);
        }
    }
}