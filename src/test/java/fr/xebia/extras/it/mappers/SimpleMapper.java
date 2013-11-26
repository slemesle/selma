package fr.xebia.extras.it.mappers;

import fr.xebia.extras.beans.EnumIn;
import fr.xebia.extras.beans.EnumOut;
import fr.xebia.extras.mapper.Mapper;

import java.math.BigInteger;
import java.util.*;

/**
 *
 */
@Mapper
public interface SimpleMapper {

    String convert(String in);

    int convert(int in);

    boolean convert(boolean in);

    byte convert(byte in);

    char convert(char in);

    Integer convert(Integer in);

    char[] convert(char[] in);

    Character[] convert(Character[] in);

    String[] convert(String[] in);

    List<String> convert(List<String> in);

    HashSet<String> convert(HashSet<String> in);

    Map<String, String> convert(Map<String, String> in);

    EnumOut convert(EnumIn in);

    String[][] convert(String[][] in);

    String[][][] convert(String[][][] in);

    HashSet<String> convertSetToHashSet(Set<String> in);

    Set<String> convertLnkedHashSetToSet(LinkedHashSet<String> in);

    HashMap<String,Date> convertStringToDateMap(TreeMap<String, Date> in);

    Map<BigInteger,Date> convertBigIntToDateMap(Map<BigInteger, Date> in);

    BigInteger convertBigInt(BigInteger in);

    Date convertDate(Date in);
}
