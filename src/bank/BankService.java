package bank;

import java.util.ArrayList;
import java.util.List;

public class BankService {
	private List<Bank> accounts = new ArrayList<>();
	private String number;
	private int balance;
	
	// 계좌 검색
	private int findAccount(String number) {
		// 양식에 맞게 - 삽입
		number = number.substring(0, 3) + "-" + number.substring(3);
		int accountIndex = -1;
		for (Bank account : accounts) {
			// 계좌번호에 맞는 계좌를 검색하여 해당 인덱스 저장
			if (account.toString().contains(number)) {
				accountIndex = accounts.indexOf(account);
				break;
			}
		}
		// 계좌의 인덱스 반환
		return accountIndex;
	}

	// 1.계좌개설
	public void createAccount(String owner, int password) {
		// 1000~10000원 지급
		balance = (int) (Math.round((Math.random() * 10000 + 500) / 1000) * 1000);
		System.out.println("축하합니다! " + balance + "원에 당첨되었습니다.");
		// 계좌번호 뒷자리 부여
		number = String.format("110-%03d", accounts.size() + 1);
		// 신규 계좌를 0번 리스트에 입력 및 출력
		accounts.add(new Bank(number, owner, password, balance));
		System.out.println(accounts.get(accounts.size() - 1));
	}

	// 2.계좌조회
	public void showAccount(String number, int password) {
		// 계좌번호에 맞는 계좌를 검색하여 해당 인덱스 저장
		int accountIndex = findAccount(number);
		// 계좌 비밀번호가 맞는지 검사
		if (accounts.get(accountIndex).getPassword() == password) {
			System.out.println(accounts.get(accountIndex));
		} else {
			System.out.println("잘못된 계좌번호거나 비밀번호가 틀립니다.");
		}
	}

	// 3.입금
	public void bankDeposit(String number, int balance) {
		// 계좌번호에 맞는 계좌를 검색하여 해당 인덱스 저장
		int accountIndex = findAccount(number);
		switch (accountIndex) {
		case -1: // 계좌번호에 맞는 계좌가 없을 시
			System.out.println("없는 계좌입니다.");
			break;
		default:
			// 계좌의 잔고를 불러와 입금액을 더한 뒤 잔고를 업데이트
			accounts.get(accountIndex).setBalance(accounts.get(accountIndex).getBalance() + balance);
			// 업데이트된 계좌 출력
			System.out.println(accounts.get(accountIndex));
		}
	}

	// 4.출금
	public void bankWithdraw(String number, int password, int balance) {
		// 계좌번호에 맞는 계좌를 검색하여 해당 인덱스 저장
		int accountIndex = findAccount(number);
		// 계좌 비밀번호가 맞는지 검사
		if (accounts.get(accountIndex).getPassword() == password) {
			// 잔고가 충분한지 검사
			int temp = accounts.get(accountIndex).getBalance() - balance;
			if (temp < 0) {
				System.out.println("잔고가 부족합니다.");
			} else {
				// 계좌의 잔고를 불러와 출금액을 뺀 뒤 잔고를 업데이트
				accounts.get(accountIndex).setBalance(temp);
				// 업데이트된 계좌 출력
				System.out.println(accounts.get(accountIndex));
			}
		} else {
			System.out.println("잘못된 계좌번호거나 비밀번호가 틀립니다.");
		}
	}

	// 5.계좌송금
	public void bankRemit(String fromNumber, String toNumber, int password, int balance) {
		// 계좌번호에 맞는 계좌를 검색하여 해당 인덱스 저장
		int toIndex = findAccount(toNumber);
		int fromIndex = findAccount(fromNumber);
		switch (toIndex) {
		case -1: // 입금 계좌번호에 맞는 계좌가 없을 시
			System.out.println("없는 계좌입니다.");
			break;
		default:
			// 잔고가 충분한지 검사
			if (accounts.get(fromIndex).getBalance() - balance < 0) {
				System.out.println("잔고가 부족합니다.");
				break;
			}
			// 출금과 입금
			bankWithdraw(fromNumber, password, balance);
			accounts.get(toIndex).setBalance(accounts.get(toIndex).getBalance() + balance);
		}
	}

	// 6.$환전
	public void bankExchange(String number, int password, int balance) {
		// 계좌번호에 맞는 계좌를 검색하여 해당 인덱스 저장
		int accountIndex = findAccount(number);
		// 계좌 비밀번호가 맞는지 검사
		if (accounts.get(accountIndex).getPassword() == password) {
			// 잔고가 충분한지 검사
			int temp = accounts.get(accountIndex).getBalance() - balance;
			if (temp < 0) {
				System.out.println("잔고가 부족합니다.");
			} else {
				// 계좌의 잔고를 불러와 출금액을 뺀 뒤 잔고를 업데이트
				accounts.get(accountIndex).setBalance(temp);
				// 환전 출력
				double dollar = (double) balance / 1237.64;
				System.out.printf("%.2f달러가 환전되었습니다.\n", dollar);
				System.out.println(accounts.get(accountIndex));
			}
		} else {
			System.out.println("잘못된 계좌번호거나 비밀번호가 틀립니다.");
		}
	}

	// 7.비밀번호변경
	public void editAccount(String number, int oldPassword, int newPassord) {
		// 계좌번호에 맞는 계좌를 검색하여 해당 인덱스 저장
		int accountIndex = findAccount(number);
		// 기존 비밀번호가 맞는지 검사
		if (accounts.get(accountIndex).getPassword() == oldPassword) {
			// 신규 비밀번호로 수정
			accounts.get(accountIndex).setPassword(newPassord);
			System.out.println("계좌 비밀번호가 변경되었습니다.");
		} else {
			System.out.println("잘못된 계좌번호거나 비밀번호가 틀립니다.");
		}
	}
}
