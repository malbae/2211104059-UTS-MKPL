package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;

	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;

	private boolean isForeigner;
	private boolean gender; // true = Laki-laki, false = Perempuan

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private String spouseName;
	private String spouseIdNumber;

	private List<Child> children;

	public Employee(EmployeeData data) {
		this.employeeId = data.employeeId;
		this.firstName = data.firstName;
		this.lastName = data.lastName;
		this.idNumber = data.idNumber;
		this.address = data.address;
		this.yearJoined = data.yearJoined;
		this.monthJoined = data.monthJoined;
		this.dayJoined = data.dayJoined;
		this.isForeigner = data.isForeigner;
		this.gender = data.gender;

		this.children = new LinkedList<>();
	}

	public void setMonthlySalary(int grade) {
		int baseSalary;

		switch (grade) {
			case 1:
				baseSalary = 3000000;
				break;
			case 2:
				baseSalary = 5000000;
				break;
			case 3:
				baseSalary = 7000000;
				break;
			default:
				throw new IllegalArgumentException("Invalid grade: " + grade);
		}

		if (isForeigner) {
			baseSalary *= 1.5;
		}

		this.monthlySalary = (int) baseSalary;
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = spouseIdNumber;
	}

	public void addChild(Child child) {
		children.add(child);
	}

	public int getAnnualIncomeTax() {
		LocalDate date = LocalDate.now();

		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		} else {
			monthWorkingInYear = 12;
		}

		boolean hasNoSpouse = spouseIdNumber == null || spouseIdNumber.isEmpty();

		return TaxFunction.calculateTax(
			monthlySalary,
			otherMonthlyIncome,
			monthWorkingInYear,
			annualDeductible,
			hasNoSpouse,
			children.size()
		);
	}
}
