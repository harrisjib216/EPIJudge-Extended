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

class BinaryTreeNode<T> : TreeLike<T, BinaryTreeNode<T>?> {
    @get:Override
    override var data: T? = null

    @get:Override
    override var left: BinaryTreeNode<T>? = null

    @get:Override
    override var right: BinaryTreeNode<T>? = null

    constructor() {}
    constructor(data: T) {
        this.data = data
    }

    constructor(data: T, left: BinaryTreeNode<T>?,
                right: BinaryTreeNode<T>?) {
        this.data = data
        this.left = left
        this.right = right
    }
}