const Tarefa = (function () {

     let nome;
     let descricao;
     let prioridade;
     let status;
     let categoria;
     let dataTermino;
     let alarme;
     let horaConclusao;
     let prazoConclusao;

     return {
          getNome: function () {
               return this.nome;
          },
          setNome: function (value) {
               this.nome = value;
          },
          getDescricao: function () {
               return this.descricao;
          },
          setDescricao: function (value) {
               this.descricao = value;
          },
          getPrioridade: function () {
               return this.prioridade;
          },
          setPrioridade: function (value) {
               this.prioridade = value;
          },
          getStatus: function () {
               return this.status;
          },
          setStatus: function (value) {
               this.status = value;
          },
          getCategoria: function () {
               return this.categoria;
          },
          setCategoria: function (value) {
               this.categoria = value;
          },
          getDataTermino: function () {
               return this.dataTermino;
          },
          setDataTermino: function (value) {
               this.dataTermino = value;
          },
          getAlarme: function () {
               return this.alarme;
          },
          setAlarme: function (value) {
               this.alarme = value;
          },
          getHoraConclusao: function () {
               return this.horaConclusao;
          },
          setHoraConclusao: function (value) {
               this.horaConclusao = value;
          },
          getPrazoConclusao: function () {
               return this.prazoConclusao;
          },
          setPrazoConclusao: function (value) {
               this.prazoConclusao = value;
          }
     };

});

const GerenciamentoTarefas = (function () {

     let tarefas = [];

     return {
          adicionarTarefa: function (tarefa) {
               tarefas.push(tarefa);
          },
          removerTarefa: function (tarefa) {
               tarefas.splice(tarefas.indexOf(tarefa), 1);
          },
          listarTarefas: function () {
               return tarefas;
          },
          buscarTarefa: function (nome) {
               return tarefas.find(function (tarefa) {
                    return tarefa.getNome() === nome;
               });
          }
     };
});

const habilitarCampoAlarme = () => {
     const checkBoxSim = document.getElementById("alarme_sim");
     const campoAlarme = document.getElementById("hora_conclusao_tarefa");

     if (checkBoxSim.checked) {
          campoAlarme.disabled = false;
     } else {
          campoAlarme.disabled = true;
     }
}

const gerenciamentoTarefas = new GerenciamentoTarefas();

const capturarFormularioCriarTarefa = () => {

     const formulario = document.getElementById("forms_criar_tarefa");
     
     formulario.addEventListener('submit', function (event) {

          event.preventDefault();

          const tarefa = new Tarefa();

          tarefa.setNome(document.getElementById("nome_tarefa").value);
          tarefa.setDescricao(document.getElementById("descricao_tarefa").value);
          tarefa.setPrioridade(document.querySelector('input[name="prioridade_tarefa"]:checked').value);
          tarefa.setStatus(document.querySelector('input[name="status_tarefa"]:checked').value);
          tarefa.setCategoria(document.getElementById("categoria_tarefa").value);
          tarefa.setDataTermino(document.getElementById("data_termino_tarefa").value);
          //          tarefa.setAlarme(document.getElementById('input[name="alarme_tarefa"]:checked').value);
          tarefa.setAlarme(true);
          tarefa.setHoraConclusao(document.getElementById("hora_conclusao_tarefa").value);

          gerenciamentoTarefas.adicionarTarefa(tarefa);

          const tarefasJSON = JSON.stringify(gerenciamentoTarefas.listarTarefas());
          localStorage.setItem('tarefas', tarefasJSON);
          
     })

};

const tarefasJSON = localStorage.getItem('tarefas');
const tarefas = JSON.parse(tarefasJSON);

console.log(gerenciamentoTarefas.listarTarefas());
