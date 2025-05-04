import axios from 'axios';
import { authService } from './auth';

const api = axios.create({
  baseURL: 'http://localhost:8080/api'
});

api.interceptors.request.use(config => {
  const token = authService.getToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export interface Cliente {
  id?: number;
  nome: string;
  email: string;
  telefone: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface Chamado {
  id?: number;
  clienteId: number;
  titulo: string;
  descricao: string;
  prioridade: 'BAIXA' | 'MEDIA' | 'ALTA';
  status: 'ABERTO' | 'EM_ANDAMENTO' | 'FECHADO';
  createdAt?: string;
  updatedAt?: string;
}

export interface FollowUp {
  id?: number;
  chamadoId: number;
  descricao: string;
  resultado: 'PENDENTE' | 'EM_ANDAMENTO' | 'CONCLUIDO';
  createdAt?: string;
  updatedAt?: string;
}

export const apiService = {
  // Clientes
  async getClientes() {
    const response = await api.get<Cliente[]>('/clientes');
    return response.data;
  },

  async getCliente(id: number) {
    const response = await api.get<Cliente>(`/clientes/${id}`);
    return response.data;
  },

  async createCliente(cliente: Cliente) {
    const response = await api.post<Cliente>('/clientes', cliente);
    return response.data;
  },

  async updateCliente(id: number, cliente: Cliente) {
    const response = await api.put<Cliente>(`/clientes/${id}`, cliente);
    return response.data;
  },

  async deleteCliente(id: number) {
    await api.delete(`/clientes/${id}`);
  },

  // Chamados
  async getChamados() {
    const response = await api.get<Chamado[]>('/chamados');
    return response.data;
  },

  async getChamado(id: number) {
    const response = await api.get<Chamado>(`/chamados/${id}`);
    return response.data;
  },

  async getChamadosByCliente(clienteId: number) {
    const response = await api.get<Chamado[]>(`/chamados/cliente/${clienteId}`);
    return response.data;
  },

  async createChamado(chamado: Chamado) {
    const response = await api.post<Chamado>('/chamados', chamado);
    return response.data;
  },

  async updateChamado(id: number, chamado: Chamado) {
    const response = await api.put<Chamado>(`/chamados/${id}`, chamado);
    return response.data;
  },

  async deleteChamado(id: number) {
    await api.delete(`/chamados/${id}`);
  },

  // Follow-ups
  async getFollowUps() {
    const response = await api.get<FollowUp[]>('/followups');
    return response.data;
  },

  async getFollowUp(id: number) {
    const response = await api.get<FollowUp>(`/followups/${id}`);
    return response.data;
  },

  async getFollowUpsByChamado(chamadoId: number) {
    const response = await api.get<FollowUp[]>(`/followups/chamado/${chamadoId}`);
    return response.data;
  },

  async createFollowUp(followUp: FollowUp) {
    const response = await api.post<FollowUp>('/followups', followUp);
    return response.data;
  },

  async updateFollowUp(id: number, followUp: FollowUp) {
    const response = await api.put<FollowUp>(`/followups/${id}`, followUp);
    return response.data;
  },

  async deleteFollowUp(id: number) {
    await api.delete(`/followups/${id}`);
  }
}; 