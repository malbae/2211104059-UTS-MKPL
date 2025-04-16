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

	private Spouse spouse;
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

	/**
	 * Menentukan gaji bulanan berdasarkan grade.
	 * Grade 1: Rp3.000.000, Grade 2: Rp5.000.000, Grade 3: Rp7.000.000
	 * Jika WNA, maka gaji ditambah 50%.
	 */
	public void setMonthlySalaryBasedOnGrade(int grade) {
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

		this.monthlySalary = baseSalary;
	}

	public void setAnnualDeductibleAmount(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalMonthlyIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouseData(Spouse spouse) {
		this.spouse = spouse;
	}

	public void addChildData(Child child) {
		children.add(child);
	}

	public int getAnnualIncomeTax() {
		LocalDate currentDate = LocalDate.now();

		if (currentDate.getYear() == yearJoined) {
			monthWorkingInYear = currentDate.getMonthValue() - monthJoined;
		} else {
			monthWorkingInYear = 12;
		}

		boolean hasNoSpouse = (spouse == null || spouse.getIdNumber().isEmpty());

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
