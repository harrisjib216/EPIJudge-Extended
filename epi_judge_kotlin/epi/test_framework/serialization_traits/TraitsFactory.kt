package epi.test_framework.serialization_traits

import epi.BinaryTree

object TraitsFactory {
    private val PRIMITIVE_TYPES_MAPPING: Map<Type, SerializationTrait>? = null

    init {
        PRIMITIVE_TYPES_MAPPING = object : HashMap() {
            init {
                put(String::class.java, StringTrait())
                put(Integer::class.java, IntegerTrait())
                put(Int::class.javaPrimitiveType, IntegerTrait())
                put(Short::class.java, ShortTrait())
                put(Short::class.javaPrimitiveType, ShortTrait())
                put(Long::class.java, LongTrait())
                put(Long::class.javaPrimitiveType, LongTrait())
                put(Character::class.java, CharacterTrait())
                put(Char::class.javaPrimitiveType, CharacterTrait())
                put(Boolean::class.java, BooleanTrait())
                put(Boolean::class.javaPrimitiveType, BooleanTrait())
                put(Float::class.java, FloatTrait())
                put(Float::class.javaPrimitiveType, FloatTrait())
                put(Double::class.java, DoubleTrait())
                put(Double::class.javaPrimitiveType, DoubleTrait())
            }
        }
    }

    @SuppressWarnings("unchecked")
    fun getTrait(type: Type): SerializationTrait {
        if (type.equals(Void.TYPE)) {
            return VoidTrait()
        }
        if (type is ParameterizedType) {
            val pt: ParameterizedType = type as ParameterizedType
            val ty: Type = pt.getRawType()
            if (ty.equals(List::class.java)) {
                return ListTrait(getTrait(getInnerGenericType(pt, 0)))
            }
            if (ty.equals(Iterable::class.java)) {
                return ListTrait(getTrait(getInnerGenericType(pt, 0)))
            }
            if (ty.equals(Set::class.java)) {
                return SetTrait(getTrait(getInnerGenericType(pt, 0)))
            }
            if (ty.equals(BinaryTreeNode::class.java) || ty.equals(BinaryTree::class.java) ||
                    ty.equals(BstNode::class.java)) {
                return BinaryTreeTrait(ty as Class<*>,
                        getTrait(getInnerGenericType(pt, 0)))
            }
            if (ty.equals(ListNode::class.java)) {
                return LinkedListTrait(getTrait(getInnerGenericType(pt, 0)))
            }
        }
        val mapped: SerializationTrait? = PRIMITIVE_TYPES_MAPPING!![type]
        if (mapped != null) {
            return mapped
        }
        if (type is Class) {
            val ann: EpiUserType = (type as Class).getAnnotation(EpiUserType::class.java) as EpiUserType
            return UserTypeTrait(type as Class, ann)
        }
        throw RuntimeException("Unsupported argument type: " +
                type.getTypeName())
    }

    private fun getInnerGenericType(type: Type, idx: Int): Type {
        return if (type is ParameterizedType) {
            (type as ParameterizedType).getActualTypeArguments().get(idx)
        } else {
            throw RuntimeException(type.getTypeName() +
                    " has no generic type arguments")
        }
    }
}