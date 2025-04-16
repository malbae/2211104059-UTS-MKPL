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

 BadSmell-ClassName
	private List<Child> children;
=======
 LongParameterList-SetChild
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

=======
	private List<String> childNames;
	private List<String> childIdNumbers;
 main

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
			int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;

		children = new LinkedList<>();
	}

 BadSmell-ClassName
=======
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade
	 * kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan,
	 * grade 3: 7.000.000 per bulan). Jika pegawai adalah warga negara asing gaji
	 * bulanan diperbesar sebanyak 50%
	 */
 main
 main
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

 BadSmell-ClassName
	public void addChild(Child child) {
		children.add(child);
	}

	public int getAnnualIncomeTax() {
=======
 LongParameterList-SetChild
	public void addChild(Child child) {
		children.add(child);
	}

	public int getAnnualIncomeTax() {
=======
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax() {
		// Menghitung berapa lama pegawai bekerja dalam setahun ini,
		// jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
 main
 main
		LocalDate date = LocalDate.now();

		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		} else {
			monthWorkingInYear = 12;
		}

 BadSmell-ClassName
		boolean hasNoSpouse = spouseIdNumber == null || spouseIdNumber.isEmpty();

=======
 LongParameterList-SetChild
		boolean hasNoSpouse = spouseIdNumber == null || spouseIdNumber.isEmpty();

=======
 main
 main
		return TaxFunction.calculateTax(
			monthlySalary,
			otherMonthlyIncome,
			monthWorkingInYear,
			annualDeductible,
 BadSmell-ClassName
			hasNoSpouse,
			children.size()
=======
 LongParameterList-SetChild
			hasNoSpouse,
			children.size()
=======
			spouseIdNumber == null || spouseIdNumber.isEmpty(),
			childIdNumbers.size()
 main
 main
		);
	}
}
