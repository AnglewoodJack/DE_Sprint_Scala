import java.math.MathContext

object App {
  def main(args: Array[String]): Unit = {
    val myString = "Hello, Scala!"
    println(myString)
    println("==============================================")
    println("#Task 1\n")
//    выводит фразу «Hello, Scala!» справа налево
    println(myString.reverse)
//    переводит всю фразу в нижний регистр
    println(myString.toLowerCase)
//    удаляет символ "!"
    println(myString.replace("!", ""))
//    добавляет в конец фразы «and goodbye python!»
    println(myString.substring(0, myString.length-1) + " and goodbye python!")
    println("==============================================")
    println("#Task 2\n")
//    вычисляет ежемесячный оклад сотрудника после вычета налогов
    var employee = new Employee("Старший менеджер", 2500000, 0.25, 62000)
    println(s"Salary: ${employee.YearSalary}, Bonus: ${employee.BonusPercent}, Food: ${employee.FoodCompensation}\nMonthly income: ${employee.monthIncome()}")
    println("==============================================")
    println("#Task 3\n")
//    рассчитывает для каждого сотрудника отклонение(в процентах) от среднего значения оклада на уровень всего отдела.
    val department = new Department("IT")
    department.addEmployee(employee)
    employee.YearSalary = 100
    employee.FoodCompensation = 5
    department.addEmployee(new Employee("Data Scientist", 150, employee.BonusPercent, employee.FoodCompensation))
    department.addEmployee(new Employee("Руководитль отдела", 200, employee.BonusPercent, employee.FoodCompensation))
    department.addEmployee(new Employee("Младший аналитик", 80, employee.BonusPercent, employee.FoodCompensation))
    department.addEmployee(new Employee("Data Engineer", 120, employee.BonusPercent, employee.FoodCompensation))
    department.addEmployee(new Employee("Стажер", 75, employee.BonusPercent, employee.FoodCompensation))
    department.deviation()
    println("==============================================")
    println("#Task 4\n")
//    рассчитать новую зарплату сотрудника
    employee = department.getEmployee("Data Engineer")
    if (employee != null){
      println(s"${employee.Name}'s salary change:\nbefore: ${
        employee.monthIncome().round(new MathContext(4))
      }")
      employee.changeSalary(10)
      print(s"after: ${employee.monthIncome()}\n")
    }
    print("")
    println(s"Salary stats among employees:\nmin salary: ${
      department.minSalary().round(new MathContext(4))
    }\nmax salary: ${
      department.maxSalary().round(new MathContext(4))
    }")
    println("==============================================")
    println("#Task 5\n")
//    отсортировать список сотрудников по уровню оклада от меньшего к большему
    department.addEmployee(new Employee("Директор департамента", 350, employee.BonusPercent, employee.FoodCompensation))
    department.addEmployee(new Employee("Продуктовый аналитик", 90, employee.BonusPercent, employee.FoodCompensation))
    department.sort()
    department.printEmployees()
//    номер сотрудника в списке так,
    department.addEmployee(new Employee("DevOps", 130, employee.BonusPercent, employee.FoodCompensation))
    println("----------------------------------------------")
    department.printEmployees()
    println("==============================================")
    println("#Task 6\n")
//    вывести номера сотрудников из полученного списка, которые попадают под категорию middle
    println("'Middle' category employees:")
    val middle = department.getMiddle(8,12)
    middle.foreach(e=>println(e.Name))
    println("==============================================")
    println("#Task 7\n")
//    проиндексировать зарплату каждого сотрудника
    department.indexSalary(0.07)
    department.printEmployees()
  }
}
