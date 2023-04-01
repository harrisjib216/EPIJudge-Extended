package epi

import kotlin.Throws
import epi.ABSqrt2.Number
import epi.BstToSortedList.HeadAndTail
import epi.DefectiveJugs.VolumeRange
import Map.Entry
import epi.IsStringInMatrix.Attempt
import epi.IsTreeBalanced.BalanceStatusWithHeight
import epi.KLargestInHeap.HeapEntry
import epi.LowestCommonAncestor.Status
import epi.QueueWithMaxUsingDeque
import epi.RealSquareRoot.Ordering
import epi.RefuelingSchedule.CityAndRemainingGas
import epi.SortedArraysMerge.ArrayEntry
import epi.SortIncreasingDecreasingArray.SubarrayType
import epi.StackWithMax.ElementWithCachedMax
import epi.StringTransformability.StringWithDistance
import epi.SunsetView.BuildingWithHeight

class PostingListNode(var order: Int, var next: PostingListNode,
                      var jump: PostingListNode?) {
    @Override
    override fun toString(): String {
        return String.format("(%d)%s%s", order, if (next != null) "->" else "",
                if (next != null) next.toString() else "")
    }
}