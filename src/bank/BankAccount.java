package bank;

import java.util.Scanner;

public class BankAccount {
	private static Scanner scanner = new Scanner(System.in);

	// 싱글톤

	// 필드
	private static BankAccount[] accountArray = new BankAccount[999999]; // 계좌 묶음
	private static BankAccount account; // 계좌
	private static String accountNum; // 계좌번호
	private static String owner; // 계좌주명
	private static int password; // 계좌 비밀번호
	private static double balance; // 잔고

	// 생성자
	private BankAccount(String accountNum, String owner, int password, double balance) {
		this.accountNum = accountNum;
		this.owner = owner;
		this.password = password;
		this.balance = balance;
	}

	// 메소드 - 게터&세터
	public static void setAccountArray() { // 계좌개설
		setOwner(); // 계좌주명 입력
		setPassword(); // 계좌 비밀번호 입력
		setBalance(Math.round((Math.random() * 10000 + 500) / 1000) * 1000); // 1000~10000원 지급
		System.out.println("축하합니다! " + getBalance() + "원에 당첨되었습니다.");
		for (int i = 0; i < accountArray.length; i++) {
			if (accountArray[i] == null) {
				setAccountNum(String.format("110-410-%06d", i + 1)); // 계좌번호 뒤 6자리 부여
				setAccount(getAccountNum(), getOwner(), getPassword(), getBalance());
				accountArray[i] = getAccount();
				break;
			}
		}
		System.out.println("계좌가 개설되었습니다.");
		showAccount(getAccountNum(), getPassword());
	}

	private static void setOwner() { // 계좌주명 입력
		System.out.print("개설을 희망하시는 고객님의 성함을 입력해주십시오. >> ");
		BankAccount.owner = scanner.nextLine().replace(" ", ""); // 공백 제거
	}

	private static void setPassword() { // 계좌 비밀번호 입력
		System.out.print("계좌 비밀번호로 사용할 6자리 숫자를 입력해주십시오. >> ");
		while (true) { // 조건 만족까지 실행
			String tmp = scanner.nextLine();
			if (tmp.matches("^[0-9]*$")) { // 숫자만 들었는지 검사
				BankAccount.password = Integer.parseInt(tmp);
				if ((int) (Math.log10(password) + 1) == 6) // 숫자가 6개인지 검사
					break;
				else
					System.out.println("숫자 6개를 입력해주십시오. >> ");
			} else
				System.out.println("숫자만 입력해주십시오. >> ");
		}
	}

	public static void setBalance(double balance) {
		BankAccount.balance = balance;
	}

	public static void setAccountNum(String accountNum) {
		BankAccount.accountNum = accountNum;
	}

	private static void setAccount(String setAccountNum, String setOwner, int setPassword, double setBalance) { // 계좌 개설
		BankAccount.account = new BankAccount(setAccountNum, setOwner, setPassword, setBalance);
	}

	public static String getAccountNum() {
		return accountNum;
	}

	public static String getOwner() {
		return owner;
	}

	public static int getPassword() {
		return password;
	}

	public static double getBalance() {
		return balance;
	}

	public static BankAccount getAccount() {
		return account;
	}

	public static void showAccount(String accountNumber, int accountPassword) {
		for (BankAccount showAccount : accountArray) {
			if (showAccount.toString().contains(accountNumber)) {
				System.out.println(showAccount);
				break;
			}
		}
	}

	@Override
	public String toString() {
		return "[계좌번호=" + accountNum + ", 계좌주명=" + owner + ", 계좌잔고=" + balance + "원 ]";
	}
}