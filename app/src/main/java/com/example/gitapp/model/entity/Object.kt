package com.example.gitapp.model.entity

import com.google.gson.annotations.Expose

class Object (
    @Expose
    val total_count : Long,
    @Expose
    val incomplete_results: Boolean,
    @Expose
    val items: List<GR>
)
