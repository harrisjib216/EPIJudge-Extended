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

class BstNode<T> : TreeLike<T, BstNode<T>?> {
    @get:Override
    override var data: T? = null

    @get:Override
    override var left: BstNode<T>? = null

    @get:Override
    override var right: BstNode<T>? = null

    constructor() {}
    constructor(data: T) {
        this.data = data
    }

    constructor(data: T, left: BstNode<T>?, right: BstNode<T>?) {
        this.data = data
        this.left = left
        this.right = right
    }
}