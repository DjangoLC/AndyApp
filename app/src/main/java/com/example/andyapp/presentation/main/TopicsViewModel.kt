package com.example.andyapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andyapp.data.QuestionsRepository
import com.example.andyapp.data.models.Quiz
import com.example.andyapp.data.models.Topic
import kotlinx.coroutines.launch

class TopicsViewModel(private val topicRepository: QuestionsRepository) : ViewModel() {

    private val _topics = MutableLiveData<List<Topic>>()

    fun setup() {
        getTopics()
    }

    private fun getTopics() {
        viewModelScope.launch {
            val topics = topicRepository.getTopics()
            _topics.value = topics
        }
    }

    fun createTopic() {
        viewModelScope.launch {
            topicRepository.createQuestion(
                title = "Operario Eléctrico.",
                quiz = listOf(
                    Quiz(
                        question = "Pregunta enfocada a resistencia",
                        answer = "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                        possibleAnswers = listOf(
                            "Platíqueme de la última ocasión en que dijo una opinión diferente a la de su jefe",
                            "¿Recuerda alguna situación en la que haya convertido un problema en un reto o una oportunidad?",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina"
                        )
                    ),Quiz(
                        question = "Pregunta enfocada a resistencia",
                        answer = "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                        possibleAnswers = listOf(
                            "Platíqueme de la última ocasión en que dijo una opinión diferente a la de su jefe",
                            "¿Recuerda alguna situación en la que haya convertido un problema en un reto o una oportunidad?",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina"
                        )
                    ),Quiz(
                        question = "Pregunta enfocada a resistencia",
                        answer = "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                        possibleAnswers = listOf(
                            "Platíqueme de la última ocasión en que dijo una opinión diferente a la de su jefe",
                            "¿Recuerda alguna situación en la que haya convertido un problema en un reto o una oportunidad?",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina"
                        )
                    ),Quiz(
                        question = "Pregunta enfocada a resistencia",
                        answer = "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                        possibleAnswers = listOf(
                            "Platíqueme de la última ocasión en que dijo una opinión diferente a la de su jefe",
                            "¿Recuerda alguna situación en la que haya convertido un problema en un reto o una oportunidad?",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina"
                        )
                    ),Quiz(
                        question = "Pregunta enfocada a resistencia",
                        answer = "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                        possibleAnswers = listOf(
                            "Platíqueme de la última ocasión en que dijo una opinión diferente a la de su jefe",
                            "¿Recuerda alguna situación en la que haya convertido un problema en un reto o una oportunidad?",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina"
                        )
                    ),Quiz(
                        question = "Pregunta enfocada a resistencia",
                        answer = "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                        possibleAnswers = listOf(
                            "Platíqueme de la última ocasión en que dijo una opinión diferente a la de su jefe",
                            "¿Recuerda alguna situación en la que haya convertido un problema en un reto o una oportunidad?",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina"
                        )
                    ),Quiz(
                        question = "Pregunta enfocada a resistencia",
                        answer = "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                        possibleAnswers = listOf(
                            "Platíqueme de la última ocasión en que dijo una opinión diferente a la de su jefe",
                            "¿Recuerda alguna situación en la que haya convertido un problema en un reto o una oportunidad?",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina"
                        )
                    ),Quiz(
                        question = "Pregunta enfocada a resistencia",
                        answer = "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                        possibleAnswers = listOf(
                            "Platíqueme de la última ocasión en que dijo una opinión diferente a la de su jefe",
                            "¿Recuerda alguna situación en la que haya convertido un problema en un reto o una oportunidad?",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina"
                        )
                    ),Quiz(
                        question = "Pregunta enfocada a resistencia",
                        answer = "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                        possibleAnswers = listOf(
                            "Platíqueme de la última ocasión en que dijo una opinión diferente a la de su jefe",
                            "¿Recuerda alguna situación en la que haya convertido un problema en un reto o una oportunidad?",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina"
                        )
                    ),Quiz(
                        question = "Pregunta enfocada a resistencia",
                        answer = "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                        possibleAnswers = listOf(
                            "Platíqueme de la última ocasión en que dijo una opinión diferente a la de su jefe",
                            "¿Recuerda alguna situación en la que haya convertido un problema en un reto o una oportunidad?",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                            "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina"
                        )
                    )
                )
            )

            topicRepository.createQuestion(
                title = "Entrevista para Auxiliar de Recursos Humanos.",
                quiz = listOf(
                    Quiz(),
                    Quiz(),
                    Quiz(),
                    Quiz(),
                    Quiz(),
                    Quiz(),
                )
            )

            topicRepository.createQuestion(
                title = "Entrevista para Ejecutivo de Ventas.",
                quiz = listOf(
                    Quiz(),
                    Quiz(),
                    Quiz(),
                    Quiz(),
                    Quiz(),
                    Quiz(),
                )
            )

            topicRepository.createQuestion(
                title = "Supervisor de Construcción.",
                quiz = listOf(
                    Quiz(),
                    Quiz(),
                    Quiz(),
                    Quiz(),
                    Quiz(),
                    Quiz(),
                )
            )
        }
    }

    fun onTopics(): LiveData<List<Topic>> = _topics
}