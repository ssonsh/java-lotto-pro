# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

---

## 1단계 - 학습 테스트 실습
### String 클래스에 대한 학습 테스트
**요구사항 1**
- "1,2"을 ,로 split 했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트를 구현한다.
- "1"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지에 대한 학습 테스트를 구현한다.

**요구사항 2**
- "(1,2)" 값이 주어졌을 때 String의 substring() 메소드를 활용해 ()을 제거하고 "1,2"를 반환하도록 구현한다.

**요구사항 3**
- "abc" 값이 주어졌을 때 String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오는 학습 테스트를 구현한다. 
- String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져올 때 위치 값을 벗어나면 StringIndexOutOfBoundsException이 발생하는 부분에 대한 학습 테스트를 구현한다. 
- JUnit의 @DisplayName을 활용해 테스트 메소드의 의도를 드러낸다.

### Set Collection에 대한 학습 테스트
**요구사항 1**
- Set의 size() 메소드를 활용해 Set의 크기를 확인하는 학습테스트를 구현한다.

**요구사항 2**
- Set의 contains() 메소드를 활용해 1, 2, 3의 값이 존재하는지를 확인하는 학습테스트를 구현하려한다. 
- 구현하고 보니 다음과 같이 중복 코드가 계속해서 발생한다. 
- JUnit의 ParameterizedTest를 활용해 중복 코드를 제거해 본다.

**요구사항 3**
- 요구사항 2는 contains 메소드 결과 값이 true인 경우만 테스트 가능하다. 입력 값에 따라 결과 값이 다른 경우에 대한 테스트도 가능하도록 구현한다. 
- 예를 들어 1, 2, 3 값은 contains 메소드 실행결과 true, 4, 5 값을 넣으면 false 가 반환되는 테스트를 하나의 Test Case로 구현한다.


---

## 2단계 - 문자열 덧셈 계산기
### 기능 요구사항
- 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환 (예: “” => 0, "1,2" => 3, "1,2,3" => 6, “1,2:3” => 6)
- 앞의 기본 구분자(쉼표, 콜론)외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 “//”와 “\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다. 예를 들어 “//;\n1;2;3”과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다. 
- 문자열 계산기에 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw한다.

### 기능 목록 도출
1. **문자열 분리 (Default)**
    - 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환
- [X]  쉼표(,) 또는 콜론(:) 을 기본 구분자로 정의하고 이를 처리하기 위한 정규표현식 정의
- [X]  기본 구분자를 이용해 문자열 분리

2. **문자열 분리 (Custom)**
- 앞의 기본 구분자(쉼표, 콜론)외에 커스텀 구분자를 지정할 수 있다.
- 커스텀 구분자는 문자열 앞부분의 “//”와 “\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다.
    - 예를 들어 “//;\n1;2;3”과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
- [X]  입력 문자열 앞부분의 “//”와 “\n” 사이에 위치하는 문자를 커스텀 구분자로 정의하고 이를 처리하기 위한 정규표현식 정의
- [X]  커스텀 구분자를 이용해 문자열 분리

3. **문자열 계산기 구현**
- 문자열 계산기 도메인을 구성한다.
    - [X] 계산기 도메인에는 splitAndSum 이라는 메소드를 제공하며 String 형태의 인자를 받고 결과를 Integer로 반환한다.
- 입력된 문자를 숫자로 파싱한다.
    - [X] 입력된 문자가 빈 문자열 또는 null 값을 입력할 경우 0을 반환해야 한다.
        - “” → 0
        - null → 0
    - [X] 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw
- [X] 파싱된 숫자를 모두 합하여 결과를 반환한다.

---

## 3단계 - 로또(자동)
### 기능 요구사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

### 기능 목록 도출
1. **로또 번호를 자동으로 생성한다.**
- [X]  1~45 사이의 숫자로 이뤄진 6개의 로또 번호를 생성한다.  (Collection.shuffle() 활용)
- [X]  생성된 로또 번호는 오름차순으로 정렬된다. (Collection.sort() 활용)
- [X]  로또 번호 도메인 클래스를 개발한다. (값 객체를 활용한다.)
- [X]  유효성 체크 : 로또 번호는 1~45 사이의 숫자로 서로 중복이 없어야 하며 6개의 숫자로 이뤄진다.
- [X] 로또 번호 도메인을 가지는 1급 컬랙션을 구현한다.

2. **지난 주 당첨 번호 생성**
- [X]  문자열 입력 시 “,” 콤마를 구분자로 문자열을 분리한다.
- [X]  분리된 문자열을 Integer 으로 파싱 처리
- [X]  유효성 체크 : 로또 번호는 1~45 사이의 숫자로 서로 중복이 없어야 하며 6개의 숫자로 이뤄진다.

3. **생성된 로또 번호와 지난주 당첨 번호를 비교하여 당첨 여부를 확인한다.**
- [ ]  지난 주 당첨 번호와 자동 생성된 로또 번호를 비교하여 당첨 여부를 판단한다.

4. **로또 구매 처리**
- [ ]  사용자로 부터 로또 구매 가격을 입력받는다.
- [ ]  유효성 체크 : 로또 구매 가격은 null or “” 일 수 없고 음수 일 수 없다.
- [ ]  입력받은 로또 구매가격을 바탕으로 로또 번호 생성 횟수를 판단한다. (로또 1개 가격은 1000 이다.)
- [ ]  처리된 로또 구매 횟수를 출력한다.
- [ ]  생성된 로또 번호를 출력한다.

5. **지난 주 당첨번호 입력 처리**
- [ ]  사용자로부터 지난 주 로또 당첨 번호를 입력 받는다.

6. **로또 당첨 결과 통계 처리**
- [ ]  구매한 로또 번호의 당첨 여부를 판단하여 통계를 출력한다.
- [ ]  총 수익률을 계산한다.
- [ ]  총 수익률을 출력한다.