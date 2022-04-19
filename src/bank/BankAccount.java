package bank;

public class BankAccount {
	// 필드
	private String accountNumber; // 계좌번호
	private String accountOwner; // 계좌주명
	private int accountPassword; // 계좌 비밀번호
	private int accountBalance; // 계좌잔고

	// 생성자
	public BankAccount(String accountNumber, String accountOwner, int accountPassword, int accountBalance) {
		this.accountNumber = accountNumber;
		this.accountOwner = accountOwner;
		this.accountPassword = accountPassword;
		this.accountBalance = accountBalance;
	}

	// 게터&세터
	public String getAccountOwner() {
		return accountOwner;
	}

	public int getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(int accountPassword) {
		this.accountPassword = accountPassword;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return accountOwner + " 고객님의 계좌: " + "[계좌번호=" + accountNumber + ", 계좌잔고=" + accountBalance + "원]";
	}
}
