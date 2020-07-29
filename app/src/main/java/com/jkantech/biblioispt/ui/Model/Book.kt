package com.jkantech.biblioispt.ui.Model

import android.os.Parcel
import android.os.Parcelable
import java.lang.reflect.Constructor


class Book (
  var id:Int?=0,
  var title: String?="",
  var category: String? ="",
  var short_description: String? ="",
  var description: String? = "",
  var image: String?="",
  var url: String? ="",
  var format: String?=""

)