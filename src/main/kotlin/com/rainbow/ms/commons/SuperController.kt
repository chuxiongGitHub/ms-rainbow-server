package com.rainbow.ms.commons

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


open class SuperController<S : ISuperService<T>, T : SuperEntity> {

    @Suppress("SpringKotlinAutowiredMembers")
    @Autowired
    protected lateinit var baseService: S

    @GetMapping("/{id}")
    open fun get(@PathVariable("id") id: Long): T? = baseService.selectById(id)

    @PostMapping
    open fun create(@RequestBody t: T) = baseService.insert(t)

    @PutMapping("/{id}")
    open fun update(@PathVariable id: Long, @RequestBody t: T) = baseService.updateById(t.apply { this.id })

    @DeleteMapping("/{id}")
    open fun delete(@PathVariable id: Long) = baseService.deleteById(id)
}