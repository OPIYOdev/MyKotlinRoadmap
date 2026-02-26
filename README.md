Bradley Opiyo

Android/Kotlin Developer → Google L4 Engineer
Nairobi, Kenya | bradleyopiyo.dev | bradley@opiyo.dev

[
🚀 Kotlin Mastery Roadmap

72hr Sprint → Google Android Interview Ready
Progress: CARWASH SaaS (Production Kotlin/Compose) → L4 Feature Ownership

text
graph TD
    A[Kotlin Fundamentals] --> B[Null Safety]
    B --> C[Coroutines/Flows]
    C --> D[MVVM + Clean Arch]
    D --> E[Jetpack Compose]
    E --> F[Production Patterns]
    F --> G[Google L4 Ready]
    
    style A fill:#e1f5fe
    style G fill:#c8e6c9

Phase 1: Kotlin Core (Day 1 - Complete)

✅ Null Safety | ✅ Sealed Classes | ✅ Extensions

    String? → let/elvis ?: / !! → Safe calls everywhere

    sealed class UiState { Success(data), Loading, Error(msg) }

    CARWASH: fun parseTenantId(url: String?) = url?.split("/")?.get(3) ?: ""

Phase 2: Coroutines Mastery (Day 2 - In Progress)

Status: 60% → Target: 100% by Feb 28
Skill	Status	Practice	CARWASH Example
launch/async	🟡 70%	10 LeetCode	viewModelScope.launch { repo.getServices().collect { } }
Flows/StateFlow	🟢 85%	API streams	stateIn(WhileSubscribed(5s), replay=1)
Error Handling	🟡 50%	catch { emit(Error) }	Tenant API failures
Target: 3h → Flow-based service catalog			

Daily Drill:

kotlin
// Parallel API calls (CARWASH service fetch)
coroutineScope {
    val services = async { repo.getServices() }
    val tenants = async { repo.getTenants() }
    updateUi(services.await(), tenants.await())
}

Phase 3: MVVM + Clean Architecture (Day 3)

Target: Production-ready patterns from CARWASH

kotlin
// ViewModel → Repository → DataSource (CARWASH Pattern)
@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val repo: ServiceRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<ServiceUiState>(Loading)
    val uiState: StateFlow<ServiceUiState> = _uiState.asStateFlow()
    
    init { viewModelScope.launch { getServices() } }
    
    private suspend fun getServices() {
        _uiState.value = try {
            val services = repo.getServices(tenantId).first()
            Success(services)
        } catch (e: Exception) { Error(e.message) }
    }
}

Repository Pattern (Offline-first):

kotlin
class ServiceRepository @Inject constructor(
    private val remote: ServiceRemoteDataSource,
    private val local: ServiceLocalDataSource
) {
    fun getServices(tenantId: String): Flow<List<Service>> = 
        remote.getServices(tenantId)
            .catch { emit(local.getServices(tenantId)) }
            .onEach { local.saveServices(it, tenantId) }
}

Phase 4: Jetpack Compose Integration

text
remember vs rememberSaveable
State Hoisting → Parent owns, children receive
Layout: Column/Row/Box + Modifier chains
Testing: composeTestRule.onNodeWithText("Service").assertExists()

🏗️ CARWASH SaaS (Production Portfolio)

Multi-tenant carwash booking platform → 50+ users, 1K req/day, 100% uptime
Feature	Kotlin Implementation	Impact
API Layer	Retrofit + JWT + Flows	40% latency ↓
Offline	Room + WorkManager	Zero-downtime UX
Multi-Tenant	TenantContext injection	100% isolation
UI State	MVVM + StateFlow	Realtime updates

Live: [Demo Link] | Code: github.com/bradleyopiyo/carwash
📚 Learning Stack (72hr Sprint)
Resource	Focus	Time
Kotlin Coroutines Guide	Flows/StateFlow	2h/day
Android Codelabs	Compose/MVVM	3h/day
LeetCode Kotlin	DSA (Trees/Graphs)	1h/day
CARWASH Repo	Verbalize achievements	30min

Daily Commitment: 6h → Code, Build, Explain → Record mocks
🎯 Google L4 Target Skills (Week 1 Mastery)

kotlin
// Interview-ready: CARWASH → Google
✅ Null Safety → Parse APIs safely
✅ Coroutines → Parallel tenant fetches
🔄 Flows → Service catalog streams  
🔄 MVVM → State hoisting + testing
🔄 Compose → Service catalog UI
✅ Clean Arch → Repository pattern
✅ Hilt → Dependency injection
✅ Testing → 85% coverage

Progress Tracker:

text
[ ] Day 2: Coroutines 100% + 15 LeetCode
[ ] Day 3: MVVM diagrams + Compose screens  
[X] CARWASH: Production Kotlin reference
[X] CV: Google L4 aligned

🤝 Let's Connect

[
[
[

Open to: Google Android L4 | Production Kotlin roles | Mentorship

Updated Feb 26, 2026 | 72hr Google Interview Sprint | Nairobi → Silicon Valley
"Production-ready engineer building scalable Android experiences"
