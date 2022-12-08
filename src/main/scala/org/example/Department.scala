import java.math.MathContext
import java.util
import scala.collection.convert.ImplicitConversions.`iterator asScala`

class Department(name: String) {
  private val _employees: util.ArrayList[Employee] = new util.ArrayList[Employee]()
//  добавление работника с сортировкой по зарплате
  def addEmployee(employee: Employee): Unit = {
    var index = 0
    _employees.forEach(e=>{
      if (e.monthIncome() < employee.monthIncome())
        index += 1
    })
    _employees.add(index, employee)
  }
//  удаление (увольнение) работника
  def fireEmployee(name: String): Unit = {
    _employees.removeIf(e => e.Name == name)
  }
//  поиск работника
  def getEmployee(name: String): Employee = {
    val it = _employees.iterator
    while (it.hasNext) {
      val e = it.next()
      if (e.Name == name)
        return e
    }
    null
  }
//  средняя месячная зарплата по всем работникам
  def averageSalary(): BigDecimal = {
    var sum: BigDecimal = 0
    _employees.forEach(e => sum += e.monthIncome())
    sum / _employees.size()
  }
//  отклонение по зарпоате в процнтах
  def deviation(): Unit = {
    val average = averageSalary()
    println(s"Average month salary: ${average.round(new MathContext(2))}")
    _employees.forEach(e => {
      val percent_diff = ((e.monthIncome() / average - 1) * 100).round(new MathContext(2))
      println(s"${e.Name}: ${percent_diff}%")
    }
    )
  }
//  максимальная зарплата
  def maxSalary(): BigDecimal = {
    var max: BigDecimal = 0
    _employees.forEach(e => {
      val salary = e.monthIncome()
      if (max < salary) {
        max = salary
      }
    })
    max
  }
//  минимальная зарплата
  def minSalary(): BigDecimal = {
    var min: BigDecimal = if (_employees.size() == 0) 0 else _employees.get(0).monthIncome()
    _employees.forEach(e => {
      val salary = e.monthIncome()
      if (min > salary) {
        min = salary
      }
    })
    min
  }
//  сортировка по зарплате
  def sort(): Unit = {
    import java.util.Collections
    import java.util.Comparator
    Collections.sort(_employees, new Comparator[Employee]() {
      override def compare(o1: Employee, o2: Employee): Int = {
        if (o1.YearSalary == o2.YearSalary) return 0
        if (o1.YearSalary < o2.YearSalary) -1
        else 1
      }
    })
  }
//  показать сотрудников
  def printEmployees(): Unit = {
    println(s"Department $name employees month salary:")
    _employees.forEach(e => {
      println(s"${e.Name}: ${e.monthIncome().round(new MathContext(4))}")
    })
  }
//  сотрудники уровня middle
  def getMiddle(from:BigDecimal, to: BigDecimal): List[Employee] ={
    _employees.iterator().toList.filter(e=>e.monthIncome() > from && e.monthIncome() < to)
  }
//  индексация зарплат
  def indexSalary(percent: Double): Unit ={
    _employees.forEach(e=>{
      e.indexSalary(percent)
    })
  }
}