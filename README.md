# DAA_assignment1

1. Architecture Notes:

4 algorithms are implemented: MergeSort, QuickSort, Deterministic Select, and Closest Pair.

MergeSort: Uses a cutoff to InsertionSort for small subarrays (CUTOFF = 16).  
A single reusable buffer is allocated once and reused in each merge step, which reduces memory allocations and improves cache locality.  
Recursion depth is controlled by halting recursion on subarrays smaller than the cutoff. 

QuickSort: Uses a randomized pivot to avoid adversarial inputs.  
Recurses only on the smaller partition, while iterating on the larger partition. This ensures recursion depth is bounded by O(log n).  
Memory usage is minimal since no additional arrays are allocated.  

Deterministic Select: Implements the Median-of-Medians algorithm, which guarantees O(n) worst-case complexity.  
Depth is controlled by always reducing the problem size by at least 30%.  
Allocations are avoided by working in-place.  

Closest Pair: Uses a divide-and-conquer strategy: points are sorted by X once, and recursion splits into halves.  
Additional memory usage is controlled by passing sorted arrays and avoiding redundant copies.  
Recursion depth is O(log n), as the problem size halves each time.

2. Recurrence Analysis:

MergeSort:
  Recurrence: T(n) = 2T(n/2) + O(n).  
  By Master Theorem (Case 2), this resolves to Θ(n log n).  
  Cutoff to InsertionSort reduces the constant factor, since insertion is faster for small n.  

QuickSort:
  Recurrence (expected): T(n) = T(n/2) + O(n).  
  By Master Theorem (Case 2), this gives Θ(n log n).  
  Worst-case is O(n²), but randomized pivot avoids it with high probability.  
  Recursion depth bounded by O(log n).  

Deterministic Select: 
  Recurrence: T(n) = T(n/5) + T(7n/10) + O(n).  
  By Akra–Bazzi, the solution is Θ(n).  
  This shows guaranteed linear time even in worst case.

Closest Pair:  
  Recurrence: T(n) = 2T(n/2) + O(n).  
  Master Theorem (Case 2) gives Θ(n log n).  
  The divide step dominates, while the combine step runs in linear time with pruning by strip size. 

3.1 Time vs Input Size

- MergeSort and QuickSort both show O(n log n) growth.  
- QuickSort is faster in practice due to better cache performance and fewer allocations.  
- Deterministic Select grows linearly, but has larger constant factors.  
- Closest Pair behaves as expected with O(n log n) growth.  

<img width="983" height="828" alt="timevsinput" src="https://github.com/user-attachments/assets/06e7b63f-37f1-40d4-9d83-ac2d6377b0da" />

3.2 Recursion Depth (QuickSort)

<img width="992" height="826" alt="depth" src="https://github.com/user-attachments/assets/fc6b258b-248a-4795-b734-63f50f1a777e" />

- Depth grows approximately as 2·log₂(n).  
- Confirms randomized pivot and recursion strategy successfully limit stack usage.

3.3 Constant-Factor Effects
Cache: MergeSort’s reusable buffer improves locality and reduces allocations.  
Garbage Collection: Avoiding per-call allocations prevents GC pauses.  
Pivot Choice: Randomization prevents worst-case inputs but may add minor overhead. 

4. Summary

Theory vs Practice Alignment:
  - MergeSort: O(n log n) in theory and practice.  
  - QuickSort: O(n log n) on average, confirmed empirically; recursion depth bounded.  
  - Deterministic Select: Linear theoretical complexity confirmed, though slower on small n.  
  - Closest Pair: Matches O(n log n) growth.  

Mismatch Sources: 
  - Constant factors (cache effects, branch prediction, GC) make QuickSort faster than MergeSort despite the same asymptotics.  
  - Deterministic Select is asymptotically optimal but impractical for small sizes due to large constants.  
