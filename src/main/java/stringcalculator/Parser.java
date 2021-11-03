package stringcalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 주어진 문자열에대해 구분자문와 숫자문를 구분하며
 * 구분된 항목을 통해 숫자들을 추출한다.
 */
public class Parser {
  private static final String CUSTOM_SEPARATOR_PARSING_PATTERN = "^//(.)\n(.*)$";
  private static final Integer REGEX_GROUP_SEPARATOR_INDEX = 1;
  private static final Integer REGEX_GROUP_NUMBER_INDEX = 2;

  private final Pattern customSeparatorParsingPattern;
  private final Separators defaultSeparators;

  /**
   * 계산할 문자열 파서 초기화.
   */
  public Parser() {
    this.defaultSeparators = generateDefaultSeparators();
    this.customSeparatorParsingPattern = Pattern.compile(CUSTOM_SEPARATOR_PARSING_PATTERN);
  }

  private Separators generateDefaultSeparators() {
    List<Separator> separators = new ArrayList<>();

    separators.add(new Separator(","));
    separators.add(new Separator(":"));

    return new Separators(separators);
  }

  /**
   * 문자열을 통해 계산에 사용되는 숫자를 추출한다.
   *
   * @param parsingText 구분자와 숫자가 포함된 문자열
   * @return 계산에 사용되는 숫자
   */
  public NaturalNumbers parse(String parsingText) {
    ParsingElement parsingElement = generateParsingElementWithCustomSeparator(parsingText)
                                    .orElse(new ParsingElement(parsingText, this.defaultSeparators));

    return generateNaturalNumbers(parsingElement);
  }

  private Optional<ParsingElement> generateParsingElementWithCustomSeparator(String parsingText) {
    Matcher matcher = this.customSeparatorParsingPattern.matcher(parsingText);

    if (matcher.find()) {
      Separators separators = new Separators(this.defaultSeparators);
      separators.add(new Separator(matcher.group(REGEX_GROUP_SEPARATOR_INDEX)));

      String numberText = matcher.group(REGEX_GROUP_NUMBER_INDEX);
      return Optional.of(new ParsingElement(numberText, separators));
    }

    return Optional.empty();
  }

  private NaturalNumbers generateNaturalNumbers(ParsingElement parsingElement) {
    return Stream.of(getNaturalNumberBySeperators(parsingElement))
                  .map(NaturalNumber::new)
                  .collect(Collectors.collectingAndThen(Collectors.toList(), NaturalNumbers::new));
  }

  private String[] getNaturalNumberBySeperators(ParsingElement parsingElement) {
    String seperatorRegex = getSeperatorRegex(parsingElement.getSeparators().value());

    return parsingElement.getNaturalNumberText().split(seperatorRegex);
  }

  private String getSeperatorRegex(String separator) {
    return "[" + replaceSpecialCharacter(separator) + "]";
  }

  private String replaceSpecialCharacter(String separator) {
    return separator.replace("[", "\\[")
                    .replace("]", "\\]");
  }
}