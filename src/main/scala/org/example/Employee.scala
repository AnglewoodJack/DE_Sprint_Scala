class Employee(name: String, yearSalary: BigDecimal, bonusPercent: Double, foodCompensation: BigDecimal) {
  var YearSalary: BigDecimal = yearSalary // годовая зарплата
  var BonusPercent: Double = bonusPercent // процент премии
  var FoodCompensation: BigDecimal = foodCompensation // компенсация питания
  var Name: String = name
//  годовой доход
  def monthIncome(): BigDecimal = {
    (YearSalary * 0.87 + YearSalary * BonusPercent + FoodCompensation) / 12
  }
//  изменение зарплаты
  def changeSalary(salaryChange: BigDecimal): Unit ={
    YearSalary += salaryChange
  }
//  индексация зарплаты
  def indexSalary(percent: BigDecimal): Unit = {
    YearSalary += YearSalary*percent
  }
}