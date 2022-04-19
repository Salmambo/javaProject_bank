package bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankApplication {
	private static Scanner scanner = new Scanner(System.in);
	private static List<BankAccount> accounts = new ArrayList<>();
	private static String accountNumber;
	private static String accountOwner;
	private static int accountPassword;
	private static int accountBalance;

	public static void main(String[] args) {
		boolean run = true;
		while (run) {
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("1.계좌개설 | 2.계좌조회 | 3.입금 | 4.출금 | 5.계좌송금 | 6.$환전 | 7.비밀번호변경 | 8.종료");
			System.out.println("---------------지금 계좌개설하면 즉시 현금 지급 이벤트!(계좌로 입금됩니다.)---------------");
			System.out.print("실행하실 항목의 번호를 입력해주십시오. >> ");

			String selectNo = scanner.nextLine();

			switch (Integer.parseInt(selectNo)) { // 선택 번호에 따른 메소드 실행
			case 1:
				System.out.println("------------------------------------------------");
				System.out.println("=====================계좌개설=====================");
				createAccount();
				break;
			case 2:
				System.out.println("------------------------------------------------");
				System.out.println("=====================계좌조회=====================");
				showAccount();
				break;
			case 3:
				System.out.println("------------------------------------------------");
				System.out.println("===================== 입   금 =====================");
				bankDeposit();
				break;
			case 4:
				System.out.println("------------------------------------------------");
				System.out.println("===================== 출   금 =====================");
				bankWithdraw();
				break;
			case 5:
				System.out.println("------------------------------------------------");
				System.out.println("=====================계좌송금=====================");
				bankRemit();
				break;
			case 6:
				System.out.println("------------------------------------------------");
				System.out.println("===================== $환 전 =====================");
				bankExchange();
				break;
			case 7:
				System.out.println("-------------------------------------------------");
				System.out.println("====================비밀번호변경====================");
				editAccount();
				break;
			default:
				System.out.print("1~8 중에서 입력해주십시오. >> ");
				break;
			case 8:
				System.out.println("안녕히 가십시오.");
				run = false;
			}
		}
	}

	// 1.계좌개설
	public static void createAccount() {
		accountOwner = owner(); // 계좌주명 입력
		accountPassword = password(); // 계좌 비밀번호 입력
		accountBalance = (int) (Math.round((Math.random() * 10000 + 500) / 1000) * 1000); // 1000~10000원 지급
		System.out.println("축하합니다! " + accountBalance + "원에 당첨되었습니다.");
		accountNumber = String.format("110-410-%06d", accounts.size() + 1); // 계좌번호 뒷자리 부여
		// 신규 계좌를 0번 리스트에 입력 및 출력
		accounts.add(0, new BankAccount(accountNumber, accountOwner, accountPassword, accountBalance));
		System.out.println("계좌가 개설되었습니다.");
		System.out.println(accounts.get(0));
	}

	// 계좌주명 입력
	private static String owner() {
		System.out.print("개설을 희망하시는 고객님의 성함을 입력해주십시오. >> ");
		return scanner.nextLine().replace(" ", "");
	}

	// 계좌 비밀번호 입력
	private static int password() {
		System.out.print("계좌 비밀번호로 사용할 6자리 숫자를 입력해주십시오. >> ");
		while (true) { // 조건 만족까지 실행
			String tmp = scanner.nextLine();
			if (tmp.matches("^[0-9]*$")) { // 숫자만 들었는지 검사
				int password = Integer.parseInt(tmp);
				if ((int) (Math.log10(password) + 1) == 6) // 숫자가 6개인지 검사
					return password;
				else
					System.out.print("숫자 6개를 입력해주십시오. >> ");
			} else
				System.out.print("숫자만 입력해주십시오. >> ");
		}
	}

	// 2.계좌조회
	public static void showAccount() {
		System.out.print("조회할 계좌 번호를 입력해주십시오. >> ");
		String tmp = scanner.nextLine().replaceAll(" |-", ""); // 공백, - 제거
		tmp = tmp.substring(0, 3) + "-" + tmp.substring(3, 6) + "-" + tmp.substring(6); // 양식에 맞게 - 삽입
		int accountIndex = accounts.indexOf(findAccount(tmp)); // 계좌번호에 맞는 계좌를 검색하여 해당 인덱스 저장
		switch (accountIndex) { // 인덱스에 따른 루트
		case -1: // 계좌번호에 맞는 계좌가 없을 시
			System.out.println("없는 계좌입니다.");
			break;
		default:
			System.out.print("계좌 비밀번호를 입력해주십시오. >> ");
			tmp = scanner.nextLine();
			// 계좌의 비밀번호와 맞는지 검사
			if (accounts.get(accountIndex).getAccountPassword() == Integer.parseInt(tmp))
				System.out.println(accounts.get(accountIndex));
			else
				System.out.println("비밀번호가 틀립니다.");
		}
	}

	// 계좌 검색
	private static Object findAccount(String accountNumber) {
		Object findAccount = "";
		// 계좌번호가 일치하는 계좌를 반환
		for (Object account : accounts) {
			if (account.toString().contains(accountNumber)) {
				findAccount = account;
				break;
			}
		}
		return findAccount;
	}

	// 3.입금
	public static void bankDeposit() {
		System.out.print("입금할 계좌 번호를 입력해주십시오. >> ");
		String tmp = scanner.nextLine().replaceAll(" |-", ""); // 공백, - 제거
		tmp = tmp.substring(0, 3) + "-" + tmp.substring(3, 6) + "-" + tmp.substring(6); // 양식에 맞게 - 삽입
		int accountIndex = accounts.indexOf(findAccount(tmp)); // 계좌번호에 맞는 계좌를 검색하여 해당 인덱스 저장
		switch (accountIndex) { // 인덱스에 따른 루트
		case -1: // 계좌번호에 맞는 계좌가 없을 시
			System.out.println("없는 계좌입니다.");
			break;
		default:
			System.out.print("넣으실 금액을 입력해주십시오. >> ");
			tmp = scanner.nextLine();
			// 계좌의 잔고를 불러와 입금액을 더한 뒤 잔고를 업데이트
			accounts.get(accountIndex)
					.setAccountBalance(accounts.get(accountIndex).getAccountBalance() + Integer.parseInt(tmp));
			System.out.println(accounts.get(accountIndex)); // 업데이트된 계좌 출력
		}
	}

	// 4.출금
	public static void bankWithdraw() {
		System.out.print("출금할 계좌 번호를 입력해주십시오. >> ");
		String tmp = scanner.nextLine().replaceAll(" |-", ""); // 공백, - 제거
		tmp = tmp.substring(0, 3) + "-" + tmp.substring(3, 6) + "-" + tmp.substring(6); // 양식에 맞게 - 삽입
		int accountIndex = accounts.indexOf(findAccount(tmp)); // 계좌번호에 맞는 계좌를 검색하여 해당 인덱스 저장
		switch (accountIndex) { // 인덱스에 따른 루트
		case -1: // 계좌번호에 맞는 계좌가 없을 시
			System.out.println("없는 계좌입니다.");
			break;
		default:
			System.out.print("계좌 비밀번호를 입력해주십시오. >> ");
			tmp = scanner.nextLine();
			// 계좌의 비밀번호와 맞는지 검사
			if (accounts.get(accountIndex).getAccountPassword() != Integer.parseInt(tmp)) {
				System.out.println("비밀번호가 틀립니다.");
				break;
			}
			System.out.print("찾으실 금액을 입력해주십시오. >> ");
			tmp = scanner.nextLine();
			// 계좌의 잔고를 불러와 출금액을 뺀 뒤 잔고를 업데이트
			int withdraw = accounts.get(accountIndex).getAccountBalance() - Integer.parseInt(tmp);
			// 잔고가 충분한지 검사
			if (withdraw < 0) {
				System.out.println("잔고가 부족합니다.");
				break;
			}
			accounts.get(accountIndex).setAccountBalance(withdraw);
			System.out.println(accounts.get(accountIndex)); // 업데이트된 계좌 출력
		}
	}

	// 5.계좌송금
	public static void bankRemit() {
		// 보내는 계좌
		System.out.print("본인의 계좌 번호를 입력해주십시오. >> ");
		String tmp = scanner.nextLine().replaceAll(" |-", ""); // 공백, - 제거
		tmp = tmp.substring(0, 3) + "-" + tmp.substring(3, 6) + "-" + tmp.substring(6); // 양식에 맞게 - 삽입
		int accountIndex = accounts.indexOf(findAccount(tmp)); // 계좌번호에 맞는 계좌를 검색하여 해당 인덱스 저장
		switch (accountIndex) { // 인덱스에 따른 루트
		case -1: // 계좌번호에 맞는 계좌가 없을 시
			System.out.println("없는 계좌입니다.");
			break;
		default:
			System.out.print("계좌 비밀번호를 입력해주십시오. >> ");
			tmp = scanner.nextLine();
			// 계좌의 비밀번호와 맞는지 검사
			if (accounts.get(accountIndex).getAccountPassword() != Integer.parseInt(tmp)) {
				System.out.println("비밀번호가 틀립니다.");
				break;
			}
			// 받는 계좌
			System.out.print("상대의 계좌 번호를 입력해주십시오. >> ");
			tmp = scanner.nextLine().replaceAll(" |-", ""); // 공백, - 제거
			tmp = tmp.substring(0, 3) + "-" + tmp.substring(3, 6) + "-" + tmp.substring(6); // 양식에 맞게 - 삽입
			int anotherIndex = accounts.indexOf(findAccount(tmp)); // 계좌번호에 맞는 계좌를 검색하여 해당 인덱스 저장
			switch (anotherIndex) {
			case -1: // 계좌번호에 맞는 계좌가 없을 시
				System.out.println("없는 계좌입니다.");
				break;
			default:
				// 상대 이름 확인
				String owner = accounts.get(anotherIndex).getAccountOwner();
				System.out.print(owner + " > 상대의 이름이 맞으면 그 이름을, 틀리면 아무 글자를 입력해주십시오. >> ");
				tmp = scanner.nextLine().replace(" ", "");
				if (owner.equals(tmp)) {
					System.out.print("보내실 금액을 입력해주십시오. >> ");
					tmp = scanner.nextLine();
					int remit = Integer.parseInt(tmp);
					// 잔고가 충분한지 검사
					if (accounts.get(accountIndex).getAccountBalance() - remit < 0) {
						System.out.println("잔고가 부족합니다.");
						break;
					}
					// 출금과 입금
					accounts.get(accountIndex)
							.setAccountBalance(accounts.get(accountIndex).getAccountBalance() - remit);
					accounts.get(anotherIndex)
							.setAccountBalance(accounts.get(anotherIndex).getAccountBalance() + remit);
					System.out.println(accounts.get(accountIndex)); // 업데이트된 계좌 출력
				}
				break;
			}
		}
	}

	// 6.$환전
	public static void bankExchange() {
		System.out.println("현재 환율 [1 미국 달러 = 1,237.64원]");
		System.out.print("출금할 계좌 번호를 입력해주십시오. >> ");
		String tmp = scanner.nextLine().replaceAll(" |-", ""); // 공백, - 제거
		tmp = tmp.substring(0, 3) + "-" + tmp.substring(3, 6) + "-" + tmp.substring(6); // 양식에 맞게 - 삽입
		int accountIndex = accounts.indexOf(findAccount(tmp)); // 계좌번호에 맞는 계좌를 검색하여 해당 인덱스 저장
		switch (accountIndex) { // 인덱스에 따른 루트
		case -1: // 계좌번호에 맞는 계좌가 없을 시
			System.out.println("없는 계좌입니다.");
			break;
		default:
			System.out.print("계좌 비밀번호를 입력해주십시오. >> ");
			tmp = scanner.nextLine();
			// 계좌의 비밀번호와 맞는지 검사
			if (accounts.get(accountIndex).getAccountPassword() != Integer.parseInt(tmp)) {
				System.out.println("비밀번호가 틀립니다.");
				break;
			}
			System.out.print("환전하실 금액(원)을 입력해주십시오. >> ");
			tmp = scanner.nextLine();
			int exchange = Integer.parseInt(tmp);
			// 잔고가 충분한지 검사
			if (accounts.get(accountIndex).getAccountBalance() - exchange < 0) {
				System.out.println("잔고가 부족합니다.");
				break;
			}
			// 계좌의 잔고를 불러와 출금액을 뺀 뒤 잔고를 업데이트
			accounts.get(accountIndex).setAccountBalance(accounts.get(accountIndex).getAccountBalance() - exchange);
			// 달러 변환
			double dollar = (double) exchange / 1237.64;
			System.out.printf("%.2f달러가 환전되었습니다.\n", dollar);
			System.out.println(accounts.get(accountIndex)); // 업데이트된 계좌 출력
		}
	}

	// 7.비밀번호변경
	public static void editAccount() {
		System.out.print("계좌번호를 입력해주십시오. >> ");
		String tmp = scanner.nextLine().replaceAll(" |-", ""); // 공백, - 제거
		tmp = tmp.substring(0, 3) + "-" + tmp.substring(3, 6) + "-" + tmp.substring(6); // 양식에 맞게 - 삽입
		int accountIndex = accounts.indexOf(findAccount(tmp)); // 계좌번호에 맞는 계좌를 검색하여 해당 인덱스 저장
		switch (accountIndex) { // 인덱스에 따른 루트
		case -1: // 계좌번호에 맞는 계좌가 없을 시
			System.out.println("없는 계좌입니다.");
			break;
		default:
			System.out.print("계좌주의 성함을 입력해주십시오. >> ");
			tmp = scanner.nextLine().replace(" ", ""); // 공백 제거
			if (!accounts.get(accountIndex).getAccountOwner().equals(tmp)) {
				System.out.println("잘못된 입력입니다.");
				break;
			}
			int count = 0;
			while (true) {
				count++;
				System.out.print("기존 비밀번호를 입력해주십시오. >> ");
				tmp = scanner.nextLine();
				// 계좌의 비밀번호와 맞는지 검사
				if (accounts.get(accountIndex).getAccountPassword() == Integer.parseInt(tmp)) {
					System.out.print("신규 비밀번호를 입력해주십시오. >> ");
					tmp = scanner.nextLine();
					int password = Integer.parseInt(tmp);
					// 비밀번호 변경
					accounts.get(accountIndex).setAccountPassword(password);
					break;
				}
				System.out.printf("비밀번호를 %d회 틀렸습니다.(3회 시 종료)\n", count);
				if (count == 3)
					break;
			}
		}
	}
}
