package bank;

import java.util.Scanner;

public class BankApplication {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BankService bankService = new BankService();
		String number, owner, temp;
		int password, balance;
		boolean run = true;
		while (run) {
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("1.계좌개설 | 2.계좌조회 | 3.입금 | 4.출금 | 5.계좌송금 | 6.$환전 | 7.비밀번호변경 | 8.종료");
			System.out.println("---------------지금 계좌개설하면 즉시 현금 지급 이벤트!(계좌로 입금됩니다.)---------------");
			System.out.print("실행하실 항목의 번호를 입력해주십시오. >> ");
			String selectNo = scanner.nextLine().replace(" ", "");
			// 선택 번호에 따른 메소드 실행
			switch (selectNo) {
			case "1":
				System.out.println("------------------------------------------------");
				System.out.println("=====================계좌개설=====================");
				while (true) {
					System.out.print("개설을 희망하시는 고객님의 성함을 입력해주십시오. >> ");
					temp = scanner.nextLine();
					// 입력했는지 검사
					if (!"".equals(temp)) {
						owner = temp.replace(" ", "");
						while (true) {
							try {
								System.out.print("계좌 비밀번호로 사용할 6자리 숫자를 입력해주십시오. >> ");
								temp = scanner.nextLine();
								// 숫자만 들었는지 검사
								if (temp.matches("^[0-9]*$")) {
									password = Integer.parseInt(temp);
									// 숫자가 6개인지 검사
									if ((int) (Math.log10(password) + 1) == 6) {
										// 계좌개설
										System.out.println("계좌가 개설되었습니다.");
										bankService.createAccount(owner, password);
										break;
									} else
										System.out.print("숫자 6개. ");
								} else
									System.out.print("숫자만. ");
							} catch (Exception e) {
								// 비밀번호를 입력하지 않았을 때
								System.out.print("입력. ");
								continue;
							}
						}
						break;
					}
					System.out.print("입력. ");
				}
				break;
			case "2":
				System.out.println("------------------------------------------------");
				System.out.println("=====================계좌조회=====================");
				while (true) {
					System.out.print("조회할 계좌 번호를 입력해주십시오. >> ");
					// 공백, - 제거
					number = scanner.nextLine().replaceAll(" |-", "");
					// 입력했는지 검사
					if (!"".equals(number)) {
						try {
							System.out.print("계좌 비밀번호를 입력해주십시오. >> ");
							temp = scanner.nextLine();
							password = Integer.parseInt(temp);
							// 계좌조회
							bankService.showAccount(number, password);
							break;
						} catch (Exception e) {
							// 비밀번호에 숫자만 입력한 게 아닌 경우
							System.out.println("잘못된 계좌번호거나 비밀번호가 틀립니다.");
							break;
						}
					}
					System.out.print("입력. ");
				}
				break;
			case "3":
				System.out.println("------------------------------------------------");
				System.out.println("===================== 입   금 =====================");
				while (true) {
					System.out.print("입금할 계좌 번호를 입력해주십시오. >> ");
					// 공백, - 제거
					number = scanner.nextLine().replaceAll(" |-", "");
					// 입력했는지 검사
					if (!"".equals(number)) {
						while (true) {
							try {
								System.out.print("넣으실 금액을 입력해주십시오. >> ");
								temp = scanner.nextLine();
								balance = Integer.parseInt(temp);
								// 입금
								bankService.bankDeposit(number, balance);
								break;
							} catch (Exception e) {
								// 입금액에 숫자만 입력한 게 아닌 경우
								System.out.print("숫자. ");
								continue;
							}
						}
						break;
					}
					System.out.print("입력. ");
				}
				break;
			case "4":
				System.out.println("------------------------------------------------");
				System.out.println("===================== 출   금 =====================");
				while (true) {
					System.out.print("출금할 계좌 번호를 입력해주십시오. >> ");
					// 공백, - 제거
					number = scanner.nextLine().replaceAll(" |-", "");
					// 입력했는지 검사
					if (!"".equals(number)) {
						while (true) {
							try {
								System.out.print("찾으실 금액을 입력해주십시오. >> ");
								temp = scanner.nextLine();
								balance = Integer.parseInt(temp);
								System.out.print("계좌 비밀번호를 입력해주십시오. >> ");
								temp = scanner.nextLine();
								password = Integer.parseInt(temp);
								// 출금
								bankService.bankWithdraw(number, password, balance);
								break;
							} catch (Exception e) {
								// 출금액이나 비밀번호에 숫자만 입력한 게 아닌 경우
								System.out.print("숫자. ");
								continue;
							}
						}
						break;
					}
					System.out.print("입력. ");
				}
				break;
			case "5":
				System.out.println("------------------------------------------------");
				System.out.println("=====================계좌송금=====================");
				while (true) {
					System.out.print("출금할 계좌 번호를 입력해주십시오. >> ");
					// 공백, - 제거
					number = scanner.nextLine().replaceAll(" |-", "");
					// 입력했는지 검사
					if (!"".equals(number)) {
						while (true) {
							try {
								System.out.print("보내실 금액을 입력해주십시오. >> ");
								temp = scanner.nextLine();
								balance = Integer.parseInt(temp);
								System.out.print("계좌 비밀번호를 입력해주십시오. >> ");
								temp = scanner.nextLine();
								password = Integer.parseInt(temp);
								while (true) {
									System.out.print("입금 받을 계좌 번호를 입력해주십시오. >> ");
									// 공백, - 제거
									temp = scanner.nextLine().replaceAll(" |-", "");
									// 입력했는지 검사
									if (!"".equals(temp)) {
										// 계좌송금
										bankService.bankRemit(number, temp, password, balance);
										break;
									}
									System.out.print("입력. ");
								}
								break;
							} catch (Exception e) {
								// 송금액이나 비밀번호에 숫자만 입력한 게 아닌 경우
								System.out.print("숫자. ");
								continue;
							}
						}
						break;
					}
					System.out.print("입력. ");
				}
				break;
			case "6":
				System.out.println("------------------------------------------------");
				System.out.println("===================== $환 전 =====================");
				System.out.println("현재 환율 [1 미국 달러 = 1,237.64원]");
				while (true) {
					System.out.print("출금할 계좌 번호를 입력해주십시오. >> ");
					// 공백, - 제거
					number = scanner.nextLine().replaceAll(" |-", "");
					// 입력했는지 검사
					if (!"".equals(number)) {
						while (true) {
							try {
								System.out.print("환전하실 금액을 입력해주십시오. >> ");
								temp = scanner.nextLine();
								balance = Integer.parseInt(temp);
								System.out.print("계좌 비밀번호를 입력해주십시오. >> ");
								temp = scanner.nextLine();
								password = Integer.parseInt(temp);
								// 환전
								bankService.bankExchange(number, password, balance);
								break;
							} catch (Exception e) {
								// 환전액이나 비밀번호에 숫자만 입력한 게 아닌 경우
								System.out.print("숫자. ");
								continue;
							}
						}
						break;
					}
					System.out.print("입력. ");
				}
				break;
			case "7":
				System.out.println("-------------------------------------------------");
				System.out.println("====================비밀번호변경====================");
				while (true) {
					System.out.print("계좌번호를 입력해주십시오. >> ");
					// 공백, - 제거
					number = scanner.nextLine().replaceAll(" |-", "");
					// 입력했는지 검사
					if (!"".equals(number)) {
						while (true) {
							try {
								System.out.print("기존 비밀번호를 입력해주십시오. >> ");
								temp = scanner.nextLine();
								password = Integer.parseInt(temp);
								while (true) {
									try {
										System.out.print("신규 비밀번호를 입력해주십시오. >> ");
										temp = scanner.nextLine();
										// 숫자만 들었는지 검사
										if (temp.matches("^[0-9]*$")) {
											int newPassword = Integer.parseInt(temp);
											// 숫자가 6개인지 검사
											if ((int) (Math.log10(password) + 1) == 6) {
												// 비밀번호 변경
												bankService.editAccount(number, password, newPassword);
												break;
											} else
												System.out.print("숫자 6개. ");
										} else
											System.out.print("숫자만. ");
									} catch (Exception e) {
										// 신규 비밀번호에 아무것도 입력하지 않았을 때
										System.out.print("입력. ");
										continue;
									}
								}
								break;
							} catch (Exception e) {
								// 기존 비밀번호에 숫자만 입력한 게 아닌 경우
								System.out.print("숫자. ");
								continue;
							}
						}
						break;
					}
					System.out.print("입력. ");
				}
				break;
			default:
				System.out.println("============ 1 ~ 8  중에서  입력해주십시오. ============");
				break;
			case "8":
				System.out.println("안녕히 가십시오.");
				scanner.close();
				run = false;
			}
		}
	}
}
