import { useState } from 'react';
import { useQuery, useMutation, useQueryClient } from 'react-query';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import {
  Box,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  IconButton,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TextField,
  Typography
} from '@mui/material';
import { Add as AddIcon, Edit as EditIcon, Delete as DeleteIcon } from '@mui/icons-material';
import { apiService, Cliente } from '../services/api';

const schema = yup.object({
  nome: yup.string().required('Nome é obrigatório'),
  email: yup.string().email('E-mail inválido').required('E-mail é obrigatório'),
  telefone: yup.string().required('Telefone é obrigatório')
}).required();

export function Clientes() {
  const queryClient = useQueryClient();
  const [open, setOpen] = useState(false);
  const [selectedCliente, setSelectedCliente] = useState<Cliente | null>(null);
  
  const { register, handleSubmit, reset, formState: { errors } } = useForm<Cliente>({
    resolver: yupResolver(schema)
  });

  const { data: clientes } = useQuery('clientes', apiService.getClientes);

  const createMutation = useMutation(apiService.createCliente, {
    onSuccess: () => {
      queryClient.invalidateQueries('clientes');
      handleClose();
    }
  });

  const updateMutation = useMutation(
    (cliente: Cliente) => apiService.updateCliente(cliente.id!, cliente),
    {
      onSuccess: () => {
        queryClient.invalidateQueries('clientes');
        handleClose();
      }
    }
  );

  const deleteMutation = useMutation(apiService.deleteCliente, {
    onSuccess: () => {
      queryClient.invalidateQueries('clientes');
    }
  });

  const handleClickOpen = () => {
    setSelectedCliente(null);
    reset({});
    setOpen(true);
  };

  const handleEdit = (cliente: Cliente) => {
    setSelectedCliente(cliente);
    reset(cliente);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    setSelectedCliente(null);
    reset({});
  };

  const onSubmit = async (data: Cliente) => {
    if (selectedCliente) {
      updateMutation.mutate({ ...data, id: selectedCliente.id });
    } else {
      createMutation.mutate(data);
    }
  };

  return (
    <Box>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 2 }}>
        <Typography variant="h5">Clientes</Typography>
        <Button
          variant="contained"
          startIcon={<AddIcon />}
          onClick={handleClickOpen}
        >
          Novo Cliente
        </Button>
      </Box>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Nome</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Telefone</TableCell>
              <TableCell align="right">Ações</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {clientes?.map((cliente) => (
              <TableRow key={cliente.id}>
                <TableCell>{cliente.nome}</TableCell>
                <TableCell>{cliente.email}</TableCell>
                <TableCell>{cliente.telefone}</TableCell>
                <TableCell align="right">
                  <IconButton onClick={() => handleEdit(cliente)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton onClick={() => deleteMutation.mutate(cliente.id!)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>{selectedCliente ? 'Editar Cliente' : 'Novo Cliente'}</DialogTitle>
        <DialogContent>
          <Box component="form" onSubmit={handleSubmit(onSubmit)} sx={{ mt: 2 }}>
            <TextField
              fullWidth
              label="Nome"
              margin="normal"
              error={!!errors.nome}
              helperText={errors.nome?.message}
              {...register('nome')}
            />
            <TextField
              fullWidth
              label="Email"
              margin="normal"
              error={!!errors.email}
              helperText={errors.email?.message}
              {...register('email')}
            />
            <TextField
              fullWidth
              label="Telefone"
              margin="normal"
              error={!!errors.telefone}
              helperText={errors.telefone?.message}
              {...register('telefone')}
            />
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancelar</Button>
          <Button onClick={handleSubmit(onSubmit)}>
            {selectedCliente ? 'Salvar' : 'Criar'}
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
} 