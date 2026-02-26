data class Person (
    val id: String,
    val name: String,
    var department: Department? = null,
    var salary: Double = 0.0,
    var isActive: Boolean = true 
)
data class Department (
    val id: String,
    val name: String,
    var head: Manager? = null,
    var budget: Double = 0.0,
    var employees: MutableList<Person> = mutableListOf()
)
data class Manager (
    val id: String,
    val name: String,
    val experienceYears: Int,
    var teamSize: Int = 0
)

class ManagerPool {
    private val availableManagers = listOf(
        Manager("M1", "Carol", 10),
        Manager("M2", "David", 8),
        Manager("M3", "Eve", 12)
    )
    
    fun getManager(): Manager? {
        println("🔄 getManager() called!")
        return availableManagers.randomOrNull()  // Sometimes null (exhaustion)
    }
}

fun main() {
    val pool = ManagerPool()
    // null operator
    println("===========================================================================================================================")
    
    println("=== Scenario 1: New Hire (No Department) ===")
    val newHire = Person("P01","Mutai")
    println("Before: ${newHire.department?.head?.name}")
    newHire.department?.head = pool.getManager()
    println("After: ${newHire.department?.head?.name}")
    
    println("===========================================================================================================================")
    
    println("=== Scenario 2: Reorganization (Dept exists) ===")
    val compsci = Department("D001","Computer Science")
    val frank = Person("P002","Frank", compsci)    
    println("Before: ${frank.department?.head?.name}")
    frank.department?.head = pool.getManager()
    println("After: ${frank.department?.head?.name}")
    
    println("===========================================================================================================================")
    
    println("=== Scenario 3: Termination (Inactive) ===")
    val fired = Person("P003","Pat",Department("D03", "Data Science"))
    fired.isActive = false
    println("Active: ${fired.isActive}")
    fired.department?.head = pool.getManager()
    println("After: ${fired.department?.head?.name}, ${fired.department?.head?.experienceYears} years of experience")
    
    println("===========================================================================================================================")
    
    println("=== Scenario 4: Bulk Assignment ===")
    val employees = listOf(
        Person("P10","Damain"),
        Person("P11","Kate"),
        Person("P12","Andrew",Department("D01","Computer Studies")),
        Person("P13", "Tim", Department("D02","Data Science"))
    )
    employees.forEachIndexed { i, person ->
        println("Employee: $i (${person.name}): ")
        person.department?.head = pool.getManager()
        println("Manager: ${person.department?.head?.name?: "none/skipped"}")
        
    }
    
    
    println("===========================================================================================================================")
    
    println("=== Scenario 5: API Sync (Partial Data) ===")
    val apiPerson = Person("p16","james")
    println("API Data: ${apiPerson.department}")
    apiPerson.department?.head = pool.getManager()
    println("After Sync: ${apiPerson.department?.head?.name}")
    
    println("===========================================================================================================================")
    
    println("=== Scenario 6: Contractors (Temp Dept) ===")
    val tempHire = Department("TEMP1","Contructor")
    val worker = Person("P19","Faith",tempHire)
    
    worker.department?.head = pool.getManager()
    println("manager: ${worker.department?.head?.name}")
    
    
    println("===========================================================================================================================")
    
    println("=== Scenario 7: Pool Exhaustion ===")
   
    val fullDept = Department("D6", "Full Team")
    val person19 = Person("P19", "Nina", fullDept)
    
    // Force null manager
    repeat(3) { fullDept.head = pool.getManager() }  // Use up pool
    fullDept.head = pool.getManager()  // null!
    println("Pool exhausted: ${fullDept.head == null} ✅ Handles null manager")
    



    // elvis operator
    
}    
    
    
    
    
    
