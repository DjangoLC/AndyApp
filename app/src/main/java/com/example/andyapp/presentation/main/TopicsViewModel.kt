package com.example.andyapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andyapp.data.repository.QuestionsRepository
import com.example.andyapp.data.models.Quiz
import com.example.andyapp.data.models.Topic
import com.example.andyapp.presentation.BaseViewModel
import kotlinx.coroutines.launch

class TopicsViewModel(private val topicRepository: QuestionsRepository) : BaseViewModel() {

    private val _topics = MutableLiveData<List<Topic>>()

    fun setup() {
        //createTopic()
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
                    ),
                    Quiz(
                        question = "Pregunta enfocada a iniciativa",
                        answer = "Hábleme de tres actividades que haya desempeñado por espacios largos de tiempo, que hayan requerido esfuerzo y disciplina",
                        possibleAnswers = listOf(
                            "Platíqueme de la última ocasión en que dijo una opinión diferente a la de su jefe",
                            "¿Cuándo fue la última ocasión en que de forma circunstancial tuvo que tomar el control o dirigir un grupo?",
                            "¿Recuerda alguna situación en la que haya convertido un problema en un reto o una oportunidad?",
                            "Mencione un ejemplo de algo que haya hecho por encima de lo que su trabajo le exigía"
                        )
                    ),
                    Quiz(
                        question = "Pregunta enfocada a comunicación",
                        answer = "Platícame de la última situación en que alguien se acercó a usted para platicarle o comentarle un asunto delicado",
                        possibleAnswers = listOf(
                            "Platícame de la última situación en que alguien se acercó a usted para platicarle o comentarle un asunto delicado",
                            "¿Qué es para usted estar bien económicamente? ",
                            "¿Qué busca para su familia? ",
                            "¿Cómo le gusta ser reconocido?"
                        )
                    ),
                    Quiz(
                        question = "Pregunta enfocada a orientación a resultados",
                        answer = "A la fecha ¿Cuál es el logro más significativo que ha alcanzado y que deseaba con anhelo",
                        possibleAnswers = listOf(
                            "¿Que aspectos lo tienen satisfecho en el plano familiar?",
                            "¿En los últimos dos años, ¿Qué tanto se ha sentido reconocido en su vida y por qué?",
                            "¿En los últimos dos años, ¿Qué tanto se ha sentido reconocido en su vida y por qué?",
                            "A la fecha ¿Cuál es el logro más significativo que ha alcanzado y que deseaba con anhelo"
                        )
                    ),
                    Quiz(
                        question = "Pregunta enfocada a la experiencia",
                        answer = "En tu antiguo puesto cual era la maquinaria que manejabas y como la aplicabas.",
                        possibleAnswers = listOf(
                            "Mencione un ejemplo de algo que haya hecho por encima de lo que su trabajo le exigía",
                            "En tu antiguo puesto cual era la maquinaria que manejabas y como la aplicabas.",
                            "¿Cuándo fue la última vez que experimentó la sensación de que parte de la información que usted maneja fue mal interpretada por otra persona?",
                            "¿Recuerda haber tenido algún reconocimiento diferente al de sus compañeros por el cumplimiento de un trabajo?"
                        )
                    )
                )
            )

            topicRepository.createQuestion(
                title = "Entrevista para Auxiliar de Recursos Humanos.",
                quiz = listOf(

                )
            )

            topicRepository.createQuestion(
                title = "Entrevista para Ejecutivo de Ventas.",
                quiz = listOf(

                )
            )

            topicRepository.createQuestion(
                title = "Supervisor de Construcción.",
                quiz = listOf(

                )
            )
        }
    }

    fun onTopics(): LiveData<List<Topic>> = _topics
}