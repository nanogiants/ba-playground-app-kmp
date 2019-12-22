package de.appcom.kmpplayground.presentation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import de.appcom.kmpplayground.R

class SwipeToDeleteCallback<T, V : RecyclerView.ViewHolder>(
  val context: Context,
  val onItemDelete: (Int) -> Unit
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

  // no move
  override fun onMove(
    recyclerView: RecyclerView,
    viewHolder: ViewHolder,
    target: ViewHolder
  ): Boolean = false

  // swipe left to delete
  override fun onSwiped(
    viewHolder: ViewHolder,
    direction: Int
  ) {
    onItemDelete.invoke(viewHolder.adapterPosition)
  }

  override fun onChildDraw(
    canvas: Canvas,
    recyclerView: RecyclerView,
    viewHolder: ViewHolder,
    dX: Float,
    dY: Float,
    actionState: Int,
    isCurrentlyActive: Boolean
  ) {
    val itemView = viewHolder.itemView
    val itemHeight = itemView.bottom - itemView.top
    val deleteIcon =
      VectorDrawableCompat.create(context.resources, R.drawable.ic_delete, null)!!
    val iconWidth = deleteIcon.intrinsicWidth
    val iconHeight = deleteIcon.intrinsicHeight

    // draw background
    ColorDrawable().apply {
      color = Color.parseColor("#E03B2F")
      setBounds(
          itemView.right + dX.toInt(),
          itemView.top,
          itemView.right,
          itemView.bottom
      )
      draw(canvas)
    }

    // draw icon
    val iconTop = itemView.top + (itemHeight - iconHeight) / 2
    val iconMargin = (itemHeight - iconHeight) / 2
    if (dX < (iconMargin + iconWidth) * -1) {
      val iconRight = itemView.right - iconMargin
      val iconLeft = iconRight - iconWidth
      val iconBottom = iconTop + iconHeight
      deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
      deleteIcon.draw(canvas)
    }

    super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
  }

}