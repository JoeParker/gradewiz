package com.joeparker.gradewiz.database.repository

import androidx.lifecycle.LiveData
import com.joeparker.gradewiz.database.dao.ModuleDao
import com.joeparker.gradewiz.database.entity.Module

class ModuleRepository(private val moduleDao: ModuleDao) {

    val allModules: LiveData<List<Module>> = moduleDao.getAll()

    suspend fun insertModule(module: Module) {
        moduleDao.insert(module)
    }
}
