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

const manipularTarefa = () => {

     document.getElementById("forms_criar_tarefa").addEventListener('submit', function (event) {

          //event.preventDefault();

          const index = document.querySelector('#index_tarefa_editar').value;

          //console.log(`${index} - ${document.querySelector('#index_tarefa_editar').value}`);

          if (index === null) {
               const tarefa = new Tarefa();

               tarefa.setNome(document.getElementById("nome_tarefa").value);
               tarefa.setDescricao(document.getElementById("descricao_tarefa").value);
               tarefa.setPrioridade(parseInt(document.querySelector('input[name="prioridade_tarefa"]:checked').value));
               tarefa.setStatus(document.querySelector('input[name="status_tarefa"]:checked').value);
               tarefa.setCategoria(document.getElementById("categoria_tarefa").value);
               tarefa.setDataTermino(document.getElementById("data_termino_tarefa").value);
               tarefa.setAlarme(document.querySelector('input[name="alarme_tarefa"]:checked').value);
               tarefa.setHoraConclusao(document.getElementById("hora_conclusao_tarefa").value);

               gerenciamentoTarefas.adicionarTarefa(tarefa);

               const tarefasJSON = JSON.stringify(gerenciamentoTarefas.listarTarefas());
               localStorage.setItem('tarefas', tarefasJSON);

               preencherTabela();
          } else {
               console.log("estou dentro do editar")
          }

     });
};

const limparFormularioCriarTarefa = () => {
     document.getElementById("forms_criar_tarefa").reset();
};

const tarefasJSON = localStorage.getItem('tarefas');
const tarefas = JSON.parse(tarefasJSON);

if (tarefas !== null) {
     tarefas.forEach(element => {
          gerenciamentoTarefas.adicionarTarefa(element);
     });
}

const preencherTabela = () => {

     const tabela = document.querySelector("#lista_tarefas table tbody");
     let tabelaHTML = "";

     gerenciamentoTarefas.listarTarefas().forEach((tarefa, index) => {
          console.log(tarefa);

          const definirPrioridade = () => {
               switch (tarefa.prioridade) {
                    case 5:
                         return "Muito Baixa";
                    case 4:
                         return "Baixa";
                    case 3:
                         return "Média";
                    case 2:
                         return "Alta";
                    case 1:
                         return "Muito Alta";
                    default:
                         return "Não definida";
               }
          }

          const formatarData = () => {
               const data = new Date(tarefa.dataTermino);
               return data.toString() !== "Invalid Date" ? `${data.getDate()}\/${data.getMonth() + 1}\/${data.getFullYear()}` : "Não definida";;
          }

          tabelaHTML += `
               <tr>
                    <td>${index + 1}</td>
                    <td>${tarefa.nome}</td>
                    <td>${tarefa.descricao}</td>
                    <td>${formatarData()}</td>
                    <td>${tarefa.status}</td>
                    <td>${definirPrioridade()}</td>
                    <td><i class="fa fa-edit" onclick="capturarTarefaEdicao(${index})" data-bs-toggle="modal" data-bs-target="#modal_tarefas"></i></td>
                    <td><i class="fa fa-trash" style="color:red" onclick="capturarTarefaExclusao(${index})" data-bs-toggle="modal" data-bs-target="#excluir_tarefa"></i></td>

               </tr>
         `;

     });

     tabela.innerHTML = tabelaHTML;
}

const capturarTarefaExclusao = (index) => {
     const tarefa = gerenciamentoTarefas.listarTarefas()[index];
     document.querySelector('#tarefa_nome_excluir').textContent = tarefa.nome;
     document.querySelector('#tarefa_index_excluir').textContent = index;
     //console.log(index);
}

const confirmarExclusao = () => {
     const index = parseInt(document.querySelector('#tarefa_index_excluir').textContent);
     deletarTarefa(index);
}

const deletarTarefa = (index) => {

     gerenciamentoTarefas.removerTarefa(gerenciamentoTarefas.listarTarefas()[index]);

     const tarefasJSON = JSON.stringify(gerenciamentoTarefas.listarTarefas());
     localStorage.setItem('tarefas', tarefasJSON);

     preencherTabela();

}

const capturarTarefaEdicao = (index) => {
     const tarefa = gerenciamentoTarefas.listarTarefas()[index];

     //console.log(tarefa);

     document.querySelector('#index_tarefa_editar').value = index;
     document.querySelector('#nome_tarefa').value = tarefa.nome;
     document.querySelector('#descricao_tarefa').value = tarefa.descricao;
     document.querySelector('input[name="prioridade_tarefa"][value="' + tarefa.prioridade + '"]').checked = true;
     document.querySelector('input[name="status_tarefa"][value="' + tarefa.status + '"]').checked = true;
     document.querySelector('#categoria_tarefa').value = tarefa.categoria;
     document.querySelector('#data_termino_tarefa').value = tarefa.dataTermino;
     document.querySelector('input[name="alarme_tarefa"][value="' + tarefa.alarme + '"]').checked = true;
     document.querySelector('#hora_conclusao_tarefa').value = tarefa.horaConclusao;
}

if (tarefas !== null) {
     preencherTabela();
}
