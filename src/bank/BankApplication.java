package bank;

import java.util.Scanner;

public class BankApplication {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean run = true;

		while (run) {
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("1.계좌개설 | 2.계좌조회 | 3.입금 | 4.출금 | 5.계좌송금 | 6.$환전 | 7.비밀번호변경 | 8.종료");
			System.out.println("--------------------------------------------------------------------------");
			System.out.print("선택> ");

			int selectNo = scanner.nextInt();

			switch (selectNo) { // 선택 번호에 따른 메소드 실행
			case 1:
				System.out.println("------------------------------------------------");
				System.out.println("=====================계좌개설=====================");
				System.out.println("--지금 계좌개설하면 즉시 현금 지급 이벤트!(계좌로 입금됩니다.)--");
				BankAccount.setAccountArray();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			default:
				System.out.println("1~5 중에서 입력하시오.");
				break;
			case 8:
				System.out.println("프로그램 종료");
				run = false;
			}
		}

		scanner.close();
	}
}
