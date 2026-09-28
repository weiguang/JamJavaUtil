---
type: "agent_requested"
description: "Java PB Backend — Engineering & Domain Rules"
---

# Java PB Backend — Engineering & Domain Rules

## 1. JDK 8 Compatibility & API Mapping Table
When the module target is unconfirmed or set to Java 8, strictly follow this mapping table[cite: 2, 3]:

| Intent / Feature | ❌ Forbidden JDK 9+ API | ✅ Mandatory Java 8 Safe Alternative | Semantic Caveat |
| :--- | :--- | :--- | :--- |
| Empty Collections | `List.of()`, `Set.of()`, `Map.of()` | `Collections.emptyList()`, `emptySet()`, `emptyMap()` | Unmodifiable[cite: 3]. Choose mutable containers if mutation is needed[cite: 3]. |
| Collection Snapshots | `List.of(...)`, `Set.of(...)` | `Collections.unmodifiableList(new ArrayList<>(src))` | Shallow copy[cite: 3]. Factory methods reject nulls; Java 8 wrappers allow them[cite: 3]. |
| Blank String Check | `str.isBlank()` | Project StringUtil or `str == null \|\| str.trim().isEmpty()` | Check whitespace and null policy consistency[cite: 3]. |
| Optional Check | `opt.isEmpty()` | `!opt.isPresent()` | Reference itself must be non-null[cite: 3]. |
| Stream to List | `stream.toList()` | `stream.collect(Collectors.toList())` | `stream.toList()` is unmodifiable; `Collectors.toList()` mutability varies[cite: 3]. |
| Read File | `Files.readString(path)` | `new String(Files.readAllBytes(path), StandardCharsets.UTF_8)` | Suitable for bounded small files only[cite: 3]. |
| Variable Inference | `var x = ...` | Explicit type: `String x = ...` | Do not rewrite existing valid syntax[cite: 3]. |
| Data Carrier | `record Point(...)` | Explicit POJO / Lombok `@Data` (if Lombok exists) | Lombok `@Data` generates setters; use with care for immutable domain entities[cite: 3]. |

## 2. Financial Domain Safety (PB, Trading & Settlement)
- **Precision & Types**: Always use `BigDecimal` or project-defined scaled integer types[cite: 1, 2, 3]. Never use `new BigDecimal(double)`[cite: 3]. Use `BigDecimal.valueOf(double)` or String constructor[cite: 3].
- **Explicit Rounding**: Division and scale adjustments must specify `Scale` and `RoundingMode` explicitly according to business contracts[cite: 3].
- **Comparison**: Use `.compareTo()` for numerical equality checks[cite: 3]. Avoid `.equals()` unless scale identity is explicitly required[cite: 3].
- **Idempotency & State Machine**: Operations like order submission, allocation, margin checks, and cash transfers MUST enforce idempotency via business keys (`tradeId`, `clOrdID`, `settlementId`)[cite: 1, 2, 3]. Validate entity state transitions explicitly[cite: 1, 2].
- **Time Precision**: Use `java.time.*` (`Instant`, `LocalDate`, `ZonedDateTime`) with explicit market `ZoneId` (e.g., `Asia/Hong_Kong`)[cite: 2, 3]. Avoid `java.util.Date`, `Calendar`, or server-default timezones for settlement calculations[cite: 2, 3].
- **Audit & Ledger Immutability**: Historical financial records, valuation snapshots, and ledger entries must be treated as append-only[cite: 1, 2, 3]. Never edit history in place[cite: 1, 2, 3].

## 3. Execution & Memory Safety

### Netty (Only when present)
- **Non-blocking EventLoop**: Offload DB I/O, synchronous HTTP, heavy computations, and sleeps to custom bounded executors[cite: 1, 2, 3]. Never use `CallerRunsPolicy` as it pushes blocking tasks back onto the EventLoop[cite: 1, 2, 3].
- **Buffer Ownership**:
  - Release a `ByteBuf` if and only if your handler consumes and owns it[cite: 1, 2, 3].
  - Do not release buffers forwarded via `ctx.fireChannelRead()`[cite: 3].
  - `SimpleChannelInboundHandler` auto-releases messages[cite: 3]; do not release manually unless retained[cite: 3].
- **ChannelFuture**: Use non-blocking listeners (`future.addListener(...)`) instead of calling `.sync()` or `.await()` inside `EventLoop`[cite: 1, 2, 3].

### Spring & Plain Java Boundaries
- **AOP Proxies**: Self-invocation (`this.method()`) and `private` methods bypass Spring `@Transactional` or `@Async` interceptors[cite: 1, 2, 3].
- **Transaction Rollback**: Default Spring transactions roll back on unhandled `RuntimeException`s, not checked exceptions[cite: 3]. Inspect application config before adding `rollbackFor = Exception.class` blindly[cite: 3].
- **Framework Isolation**: In pure Java or Netty-only modules, do not introduce Spring context or annotations[cite: 1, 2, 3]. Use constructor/factory dependencies[cite: 1, 2, 3].

## 4. Code Style, Logging, Security & Testing

### Code Style & Comments
- **Comment Language Policy**:
  - **Existing Files**: Strictly preserve and follow the file's established comment/Javadoc language (Chinese or English)[cite: 1, 2].
  - **New / Uncommented Files**: Default to English for all new inline comments, Javadoc, and class headers[cite: 1, 2].
  - **No In-Code Tags**: Never output compatibility tags (such as `// @jdk8-compatible`) in generated source code[cite: 1, 2].

### Logging & Security
- **SLF4J Placeholders**: Use `log.error("Failed for id: {}", id, e);`[cite: 1, 2, 3]. Never concatenate strings in log statements[cite: 1, 2, 3].
- **Sensitive Data Handling**: Never log raw passwords, API tokens, unmasked client account numbers, or full trade payloads[cite: 1, 2, 3].

### Testing & Verification
- **Test Safety**: Use `EmbeddedChannel` for Netty handler tests[cite: 3]. Never connect automated test suites to production trading, clearing, or customer data systems[cite: 1, 2, 3].