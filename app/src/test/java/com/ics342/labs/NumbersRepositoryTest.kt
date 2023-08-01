package com.ics342.labs

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.UUID
import kotlin.random.Random

internal class NumbersRepositoryTest {

    @Test
    fun `If database does not have a number fetch it from the Api`() {
        // Setup
        val database = mockk<Database>()
        val api = mockk<Api>()
        val number = Number(UUID.randomUUID().toString(), Random.nextInt())
        val id = number.id

        every { database.getNumber(id) } returns null
        every { api.getNumber(id) } returns number

        // Act
        val repository = NumbersRepository(database, api)
        val result = repository.getNumber(id)

        // Assert
        assertNotNull(result)
        assertEquals(result, number)

        verify { database.getNumber(id) }
        verify { api.getNumber(id) }
    }

    @Test
    fun ifDatabaseIsEmptyShouldFetchNumbersFromApi() {
        // TODO: implement this test

        // Setup
        val database = mockk<Database>()
        val api = mockk<Api>()
        val number = Number(UUID.randomUUID().toString(), Random.nextInt())
        val id = number.id
        var emptyNumberList: List<Number> = emptyList()

        every {database.getAllNumbers()} returns emptyNumberList
        every {api.getNumbers()} returns listOf(number)
        every {database.storeNumbers(listOf(number))} just runs

        // Act
        val repository = NumbersRepository(database, api)
        val results = repository.fetchNumbers()

        // Assert
        assertNotNull(results)
        assertEquals(results, listOf(number))

        verify { database.getAllNumbers() }
        verify { api.getNumbers()}

    }
}
